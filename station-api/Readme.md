# Station-API

API для работы с Яндекс.Станцией.

## Подключение

Настройте `prop.properties`:
```properties
station.address=wss://192.168.1.96:1961/
```
 
Настройте `secure.properites`:
```
station.secure.cert=-----BEGIN CERTIFICATE----....-----END CERTIFICATE----
station.secure.token=<JWT Token>
```

Для получения состояния Яндекс.Станции
```java
StationApi.getInstance().getState()
```

Для отправки команды на Яндекс.Станцию
```java
StationApi.getInstance().sendCommand(...)
```
