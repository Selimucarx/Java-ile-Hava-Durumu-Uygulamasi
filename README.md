
# **Java ile Hava Durumu Uygulamasi**

### **Gereksinimler**
              
- SDK 20
- Internet bağlantısı
- [Kişisel API Anahtarınızı edinmek için bağlantı](https://collectapi.com/tr/) 

### **Çalıştırmak için Gerekenler**

1. **Yol:** İndirdiğiniz projenin `HavaDurumuUygulamasi\app\src\main\resources\` konumuna `config.properties` dosyası oluşturun ve içine aşağıdaki şekilde API anahtarınızı yazın:
```
API_KEY=BURAYA_APİ_KEYİNİZ
```

Sonrasında programı başlatabilirsiniz.

2. **Yol:** Aşağıdaki kod bloğundaki parçayı:

 ```java
 Properties properties = new Properties();
 try (InputStream input = WeatherApp.class.getResourceAsStream("/config.properties")) {
     properties.load(input);
 } catch (IOException e) {
     e.printStackTrace();
     return; 
 }
 String apiKey = properties.getProperty("API_KEY");
 ```

projenizden silip.

Bu kod bloğundaki parçayı ise:

```java
 httpGet.addHeader("authorization", "apikey " + apiKey);
 ```

Aşşağıdaki şeklinde değiştirin.

 ```java
 httpGet.addHeader("authorization", "apikey " + API_KEY);
 ```

Son olarak aşşağıdaki gibi sınıfa API_KEY değişkeninizi ekleyin ve projeyi başlatın.

 ```java
 public static final String API_KEY = "Api keyiniz buraya gelecek";
 ```

Uygulama başlatıldıktan sonra, hangi ilin hava durumu bilgilerini isterseniz, o ilin plakasını konsola girerek ilgili hava durumu bilgilerini edinebilirsiniz.


Bu adımları uygulamanıza rağmen projeyi çalıştıramadıysanız benimle iletişim kurabilirsiniz.

	selim.ucarx@gmail.com 
