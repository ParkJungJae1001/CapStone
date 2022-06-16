#include <SoftwareSerial.h>
 
int RX=7;
int TX=8;
int BLUE = 11;
int GREEN = 10;
int RED = 9;
SoftwareSerial bluetooth(RX, TX);
  
void setup()
{
  Serial.begin(9600);
  bluetooth.begin(9600);
  
  pinMode(RED, OUTPUT); 
  pinMode(GREEN, OUTPUT);
  pinMode(BLUE, OUTPUT); 
}
  
void loop(){
  int Value = analogRead(A0);
  int Volt = map(Value, 0, 1023, 0, 5000);
  int Distance = (21.61/(Volt - 0.1696))*1000;

  Serial.print(Distance);
  Serial.println("cm");

  if(Distance < 15){
    analogWrite(RED, 255);
    analogWrite(GREEN, 0);
    analogWrite(BLUE, 0);  
    bluetooth.write('a');
  }
  else{
   analogWrite(RED, 0);
   analogWrite(GREEN, 0);
   analogWrite(BLUE, 255);
   bluetooth.write('b');
  }
  delay(500);
}
