#include <SoftwareSerial.h>
 
int RX=7;
int TX=8;
int BLUE = 11;
int GREEN = 10;
int RED = 9;

SoftwareSerial bluetooth(RX, TX);
 
void setup(){
  pinMode(BLUE, OUTPUT);
  pinMode(RED, OUTPUT);
  pinMode(GREEN, OUTPUT);
  
  Serial.begin(9600);
  bluetooth.begin(9600);
} 
 
void loop(){
  /*
  if (bluetooth.available() > 0) {
    Serial.write(bluetooth.read());
  }
  if (Serial.available() > 0) {
    bluetooth.write(Serial.read());
  }*/
  
  if (bluetooth.available() > 0) {
    char num = char(bluetooth.read());
    if (num == '1') {
      digitalWrite(BLUE, 255);
      digitalWrite(GREEN, 0);
      digitalWrite(RED, 0);
    }
    else if (num == '2') {
      digitalWrite(BLUE, 0);
      digitalWrite(GREEN, 255);
      digitalWrite(RED, 0);
    }
  } 
  
}
