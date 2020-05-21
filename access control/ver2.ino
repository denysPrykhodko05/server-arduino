#include <SPI.h>  // стандартная в Arduino IDE библиотека для работы с SPI шиной.
#include <MFRC522.h> // Хэдер с одноимённым классом для управления РЧИ-читалкой.
#include <WiFi.h>

// Номера пинов управления РЧИ-читалкой
#define SS_PIN 10
#define RST_PIN 9

char ssid[] = "yourNetwork"; //  имя сети SSID 
char pass[] = "secretPassword";    // пароль сети (use for WPA, or use as key for WEP)
const int RGB_LED_PINUMBERS[] = { 5, 6, 7 }; // Номера пинов-выходов для RGB-светодиода
const int BUZZER_PINUMBER = 4; // номер пина управления пищалкой
const int BUTTON_PINUMBER = 2;     // номер входа, подключенный к кнопке
const int LOCK_PIN = 3;     // пин управления замком

const int NEW_CARD_CHECK_DELAY = 500; // задержка между проверками поднесённости карты в милисекундах
const int OPEN_CLOSE_DELAY = 3000; // задержка между открытием и закрытием


// if you don't want to use DNS (and reduce your sketch size)
// use the numeric IP instead of the name for the server:
//IPAddress server(74,125,232,128);  // numeric IP for Google (no DNS)
char server[] = "localhost";    // адрес сервера (using DNS)
char request[] = "GET /search?id=";
bool buttonState = 0; // переменная для хранения состояния кнопки


int status = WL_IDLE_STATUS;
WiFiClient client;

enum States { // Состояния контроллера:
  wait, check, open, close
}; States state; // текущее состояние

bool rightCard; // Для запоминания в цикле проверки верности карты, верна ли она
bool white;   // Состояние светодиода(горит ли), пока находимся в состоянии ожидания карты
int pos = 0;  // Положение ротора сервомотора (градусы)

MFRC522 mfrc522(SS_PIN, RST_PIN); // Экземпляр MFRC522 для управления РЧИ-ридером.

void setup() {
  Serial.begin(9600); // Начать общение с компом по последовательному порту
  SPI.begin();      // Инициализация SPI-шины. 
  mfrc522.PCD_Init(); // Инициализация РЧИ-читалки
  
 while (status != WL_CONNECTED) {
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);
    // Connect to WPA/WPA2 network. Change this line if using open or WEP network:
    status = WiFi.begin(ssid, pass);

    // wait 10 seconds for connection:
    delay(10000);
  }

  Serial.println("Scan PICC to see UID and type..."); // разговариваем с портом, ага.

  white = false; // До начала работы светодиод не горит
  state = wait; // Работа начинается с состояния ожидания

  // активируем на выход пины для RGB-лампочки:
  pinMode(RGB_LED_PINUMBERS[0], OUTPUT); // red
  pinMode(RGB_LED_PINUMBERS[1], OUTPUT); // green
  pinMode(RGB_LED_PINUMBERS[2], OUTPUT); // blue
  
  pinMode(LOCK_PIN, OUTPUT);

  pinMode(BUZZER_PINUMBER, OUTPUT); // активируем на выход пин для гудка
  pinMode(BUTTON_PINUMBER, INPUT); //активируем пин на вход, ждем кнопку
}


void loop() {
  switch (state){
  case wait:
    Waiting();
    return;
  case check:
    Checking();
    return;
  case open:
    Opening();
    return;
  case close:
    Closing();
    return;
  }
}

void Waiting(){
  delay(NEW_CARD_CHECK_DELAY);

  /// мигаем белым:
if (!white){
        digitalWrite(RGB_LED_PINUMBERS[0], HIGH);   // зажигаем красный
        digitalWrite(RGB_LED_PINUMBERS[1], HIGH);   // зажигаем зелёный
        digitalWrite(RGB_LED_PINUMBERS[2], HIGH);   // зажигаем синий
        white = true;
    }
    else{
        digitalWrite(RGB_LED_PINUMBERS[0], LOW);   // тушим красный
        digitalWrite(RGB_LED_PINUMBERS[1], LOW);   // тушим зелёный
        digitalWrite(RGB_LED_PINUMBERS[2], LOW);   // тушим синий
        white = false;
    }

  // проверяем наличие новой карты:
  if (mfrc522.PICC_IsNewCardPresent())
    state = check;
    // считываем значения с входа кнопки
  buttonState = digitalRead(BUTTON_PINUMBER);
  // проверяем нажата ли кнопка
  // если нажата, то buttonState будет HIGH:
  if (buttonState == HIGH) {
    state = open;
  }
}

void Checking(){
  // Желтеем:
  digitalWrite(RGB_LED_PINUMBERS[0], HIGH);   // зажигаем красный
  digitalWrite(RGB_LED_PINUMBERS[1], HIGH);   // зажигаем зелёный
  digitalWrite(RGB_LED_PINUMBERS[2], LOW);   // тушим синий
  
  // Считываем UID одной из поднесённых карточек
  if (!mfrc522.PICC_ReadCardSerial()) {
    return;
  }
  delay(500);
  byte tehUID[5]; // tehUID карты, на котороую реагируем положительно
  // Проверяем считанный UID:
  for (byte i = 0; i < 4; i++) {
      tehUID[i] = mfrc522.uid.uidByte[i];
  }
  
   tehUID[4]= 0x00;
   size_t len1 = strlen(request);
   size_t len2 = strlen((char*)tehUID);                      

   char *result = (char*)malloc(len1 + len2 + 1);

   memcpy(result, request, len1);
   memcpy(result + len1, (char*)tehUID, len2 + 1);
  
   if (client.connect(server, 3000)) {
    Serial.println("connected to server");
    // Make a HTTP request:
    client.println(*result);
    client.println("Host: localhost");
    client.println("Connection: keep-alive");
    client.println();
  }
  char c;
  if(client.available()) {
    c = client.read();
  }
  if (c=='t')
    state = open; // Открываемся, если та самая
  else{
    Serial.println("Unknown CARD.");
    digitalWrite(5, HIGH);   // зажигаем светодиод
    digitalWrite(6, LOW);   // тушим светодиод
    digitalWrite(7, LOW);   // тушим светодиод
    digitalWrite(4, HIGH);
    //buzz(4, 7000, 2000); // buzz the buzzer on pin 4 at 2500Hz for 500 milliseconds
    delay(2000); // wait a bit between buzzes
    digitalWrite(4, LOW);

    state = wait; // Ждём другую, если не
  }
}

void Opening(){
  Serial.println("OPEN");
  digitalWrite(RGB_LED_PINUMBERS[0], LOW);
  digitalWrite(RGB_LED_PINUMBERS[1], HIGH); // Светодиод горит зеленым
  digitalWrite(RGB_LED_PINUMBERS[2], LOW);
  delay(500); // имитируем активную деятельность. Считывание происходит гораздо быстрее. Эта задержка создана для красоты презентации.
  
  Open();
  state = close;
}

void Closing(){
  delay(OPEN_CLOSE_DELAY);
  digitalWrite(RGB_LED_PINUMBERS[1], LOW); // Светодиод гаснет
  Close();
  state = wait;
}

// Две процедурки поворота ротора сервы на 180:
void Open(){
  digitalWrite(LOCK_PIN, HIGH);
}
void Close(){
  digitalWrite(LOCK_PIN, LOW);
}

  
