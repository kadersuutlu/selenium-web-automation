# 🚀 Selenium & Java - QA Automation

Bu proje, **Software Test Automation** süreçlerinde Selenium 4 ve Java altyapısını kullanarak **sıfırdan adım adım gelişen, modüler ve sürdürülebilir** bir otomasyon framework'ü oluşturma yolculuğudur.

Yazılım test otomasyonu alanına yeni başlayanlar ve kendini geliştirmek isteyenler için aşama aşama büyüyen **canlı bir kaynak ve rehber** niteliği taşımaktadır.

---

## 📌 Proje Vizyonu & Yol Haritası (Evolution Roadmap)

Proje adım adım yeni bir mimari yapı, entegrasyon veya best practice eklenerek güncellenmektedir:

- [x] **Adım 1:** Selenium 4 Web Driver kurulumu, Chrome yönetimi ve ekran görüntüsü (Screenshot) alma mekanizması.
- [x] **Adım 2:** Element bekleme stratejileri (Explicit Wait / WebDriverWait) ve senkronizasyon.
- [x] **Adım 3:** Page Object Model (POM) mimarisine geçiş (`pages` ve `tests` paket yapısı).
- [x] **Adım 4:** TestNG entegrasyonu ve Assertion (Doğrulama) yapıları.
- [x] **Adım 5:** `BaseTest` ile driver lifecycle (setup/teardown) yönetimi.
- [x] **Adım 6:** Data-Driven Testing (DDT) - TestNG `@DataProvider` ile parametreli testler.
- [x] **Adım 7:** Otomatik HTML Raporlama (Allure Report).
- [x] **Adım 8:** GitHub Actions entegrasyonu ile CI/CD Pipeline (Headless test çalıştırma).

---

## 🛠️ Adım 1: Ekran Görüntüsü (Screenshot) Alma & Dosya Yönetimi

Projenin ilk aşamasında, test anının kanıtını almak (Test Proof) veya hata anında ekran görüntüsü kaydetmek için dinamik bir dosya yönetim altyapısı kurgulanmıştır.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **TakesScreenshot Interface:** `WebDriver` nesnesi `TakesScreenshot` arayüzüne casting yapılarak ekran görüntüsü alma yeteneği kazandırıldı.
- **Dinamik Klasör Yönetimi:** `screenshots/` dizininin varlığı `directory.exists()` ile kontrol edilir; yoksa `mkdir()` kullanılarak kod tarafında otomatik oluşturulur.
- **Dosya Çakışması Yönetimi:** `StandardCopyOption.REPLACE_EXISTING` parametresiyle, aynı isimli eski ekran görüntülerinin üzerine yazılarak gereksiz dosya birikimi engellenir.
- **Depo Temizliği (Repo Hygiene):** Otomatik üretilen test görselleri `.gitignore` kapsamına dahil edilmiştir.
- **Versiyonlama Standardı:** Commit mesajları [Conventional Commits](https://www.conventionalcommits.org/) standartlarına uygun olarak yapılandırılmaktadır.

---

## ⏱️ Adım 2: Explicit Wait & Senkronizasyon

Sayfa elementlerinin DOM'a asenkron şekilde gelmesi (JavaScript render süresi) nedeniyle, sabit bekleme (`Thread.sleep`) yerine koşula dayalı, dinamik bekleme mekanizması kuruldu.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **WebDriverWait:** `ExpectedConditions.visibilityOfElementLocated(...)` ile "element görünür olana kadar bekle, olur olmaz devam et" mantığı uygulandı — maksimum bekleme süresi (10 saniye) ile sınırlandırıldı.
- **elementToBeClickable:** Tıklanacak elementler (butonlar) için sadece görünürlük değil, aynı zamanda tıklanabilirlik (enabled durumu) de kontrol edildi.
- **Flaky Test Önleme:** Sabit bekleme yerine dinamik bekleme kullanılarak, yavaş ağ koşullarında testlerin yanlışlıkla başarısız olması (flakiness) riski azaltıldı.

---

## 🧩 Adım 3: Page Object Model (POM) Mimarisi

Test mantığı ile sayfa elementlerinin/locator'larının birbirinden ayrılması amacıyla Page Object Model deseni uygulandı.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **Sorumluluk Ayrımı:** Her sayfa için (`LoginPage` gibi) ayrı bir class oluşturuldu; locator'lar ve o sayfada yapılabilecek aksiyonlar (`enterUserName`, `enterPassword`, `login` vb.) bu class içinde toplandı.
- **DRY Prensibi:** Birleştirici metodlar (`login()`), kodu tekrar yazmak yerine mevcut alt metodları çağırarak (`enterUserName()`, `enterPassword()`, `clickLoginButton()`) bakımı kolaylaştırdı.
- **Merkezi Wait Yönetimi:** `WebDriverWait` nesnesi her Page class'ının kendi constructor'ında oluşturuluyor; test kodu bekleme detaylarını bilmek zorunda kalmıyor.
- **Sürdürülebilirlik:** Bir elementin locator'ı değişirse, güncelleme yalnızca ilgili Page class'ında yapılır; testler etkilenmez.

---

## ✅ Adım 4: TestNG & Assertion Yapıları

Script tabanlı `main` metodundan, gerçek doğrulama (assertion) yapan bir test framework'üne geçiş yapıldı.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **@Test Annotation:** TestNG ile test metodları tanımlandı, `main` metoduna bağımlılık ortadan kaldırıldı.
- **Assertion:** `Assert.assertTrue(...)` ile hem başarılı login (URL kontrolü) hem de başarısız login senaryoları (hata mesajı kontrolü) doğrulandı.
- **Negatif Senaryo Testi:** Kilitli kullanıcı (`locked_out_user`) ile giriş denemesi ve dönen hata mesajının içeriği test edildi.

---

## 🏗️ Adım 5: BaseTest ile Driver Lifecycle Yönetimi

Driver açma/kapama mantığı, tekrarı önlemek ve test izolasyonunu sağlamak amacıyla ortak bir `BaseTest` class'ına taşındı.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **@BeforeMethod / @AfterMethod:** Her test metodundan önce yeni bir `ChromeDriver` açılır, sonrasında kapatılır — testler birbirinden tamamen izole çalışır.
- **Miras (Inheritance):** Tüm test class'ları (`LoginTest` gibi) `BaseTest`'i extend ederek `protected WebDriver driver` alanına doğrudan erişir.
- **Headless Mod Desteği:** `ChromeOptions` ile, `-Dheadless=true` sistem property'sine göre tarayıcının görsel arayüzsüz (headless) ya da normal modda açılması kontrol edilir — bu, CI/CD entegrasyonunun (Adım 8) temelini oluşturur.

---

## 🔁 Adım 6: Data-Driven Testing (DDT)

Aynı test mantığını farklı veri setleriyle tekrar tekrar çalıştırmak için TestNG `@DataProvider` özelliği kullanıldı.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **@DataProvider:** Kilitli kullanıcı, yanlış şifre ve boş kullanıcı adı senaryoları tek bir metodda (`Object[][]`) veri seti olarak tanımlandı.
- **Parametreli Test:** `@Test(dataProvider = "invalidLoginData")` ile tek bir test metodu, tanımlı veri seti sayısı kadar (3 kez) otomatik olarak çalıştırıldı.
- **Kod Tekrarının Azaltılması:** Her senaryo için ayrı test yazmak yerine, tek bir test + veri seti yapısıyla bakım yükü azaltıldı.

---

## 📊 Adım 7: Allure Raporlama

Test sonuçlarının görsel, adım adım izlenebilir bir HTML raporuna dönüştürülmesi için Allure entegre edildi.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **allure-testng + AspectJ Weaver:** `@Step` annotation'larının işlenebilmesi için AspectJ ajanı, `maven-surefire-plugin` üzerinden `-javaagent` ile devreye alındı.
- **@Step Annotation:** `LoginPage` metodlarına eklenen adımlar (`enterUserName`, `enterPassword`, `clickLoginButton`) raporda parametreleriyle birlikte (`{userName}` gibi) görüntülenir.
- **Java 25 / AspectJ Uyumluluğu:** AspectJ Weaver'ın henüz Java 25 bytecode formatını desteklememesi nedeniyle, `maven.compiler.source/target` değeri Java 21 (LTS) olarak ayarlandı.
- **Raporu görüntülemek için:**
  ```bash
  mvn clean test
  allure serve allure-results
  ```

---

## ⚙️ Adım 8: GitHub Actions ile CI/CD Pipeline

Testlerin her `push` ve `pull request` işleminde, bulut üzerinde otomatik olarak çalıştırılması sağlandı.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

- **Workflow Tanımı:** `.github/workflows/selenium-ci.yml` dosyası, `main` branch'ine her push/PR'da tetiklenecek şekilde yapılandırıldı.
- **Ortam Kurulumu:** `actions/setup-java@v4` ile JDK 21, `browser-actions/setup-chrome@v1` ile Chrome, sanal Ubuntu sunucusuna otomatik kuruluyor.
- **Headless Test Çalıştırma:** `mvn clean test -Dheadless=true` komutu ile testler, görsel arayüz olmadan (headless) CI ortamında çalıştırılıyor.
- **Maven Cache:** `setup-java` action'ındaki `cache: 'maven'` seçeneği ile bağımlılıklar önbelleğe alınarak pipeline süresi kısaltılıyor.

---

## 💻 Kullanılan Teknolojiler

- **Language:** Java 21 (LTS)
- **Automation Tool:** Selenium WebDriver 4.46
- **Test Framework:** TestNG 7.10
- **Reporting:** Allure 2.35 + AspectJ Weaver
- **Build Tool:** Maven
- **CI/CD:** GitHub Actions (Headless Chrome)
- **Design Pattern:** Page Object Model (POM)

---

## 🚀 Projeyi Yerelde Çalıştırma

1. **Projeyi Klonlayın:**

   ```bash
   git clone https://github.com/kadersuutlu/selenium-web-automation.git
   ```

2. **Testleri çalıştırın (görsel modda):**

   ```bash
   mvn clean test
   ```

3. **Testleri headless modda çalıştırın (CI'daki gibi):**

   ```bash
   mvn clean test -Dheadless=true
   ```

4. **Allure raporunu görüntüleyin:**

   ```bash
   allure serve allure-results
   ```

---

## 📝 İlgili Yazı

Bu projenin geliştirilme sürecini anlatan yazıya buradan ulaşabilirsiniz: *(https://sutlukader.medium.com/selenium-ile-s%C4%B1f%C4%B1rdan-s%C3%BCrd%C3%BCr%C3%BClebilir-bir-test-otomasyonu-kurmak-9c9d97a7573c)*