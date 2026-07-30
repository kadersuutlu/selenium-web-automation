# 🚀 Selenium & Java - QA Automation

Bu proje, **Software Test Automation** süreçlerinde Selenium 4 ve Java altyapısını kullanarak **sıfırdan adım adım gelişen, modüler ve sürdürülebilir** bir otomasyon framework'ü oluşturma yolculuğudur. 

Yazılım test otomasyonu alanına yeni başlayanlar ve kendini geliştirmek isteyenler için aşama aşama büyüyen **canlı bir kaynak ve rehber** niteliği taşımaktadır.

---

## 📌 Proje Vizyonu & Yol Haritası (Evolution Roadmap)

Proje adım adım yeni bir mimari yapı, entegrasyon veya best Practices eklenerek güncellenmektedir:

- [x] **Adım 1:** Selenium 4 Web Driver kurulumu, Chrome yönetimi ve ekran görüntüsü (Screenshot) alma mekanizması.
- [x] **Adım 2:** Element bekleme stratejileri (Explicit Wait / WebDriverWait) ve senkronizasyon.
- [x] **Adım 3:** Page Object Model (POM) mimarisine geçiş (`pages` ve `tests` paket yapısı).
- [x] **Adım 4:** TestNG / JUnit 5 entegrasyonu ve Assertion (Doğrulama) yapıları.
- [x] **Adım 5:** `BaseTest` ve `DriverFactory` ile Singleton Pattern mantığında sürücü yönetimi.
- [x] **Adım 6:** Data-Driven Testing (DDT) - JSON/Excel veya TestNG DataProvider ile parametreli testler.
- [x] **Adım 7:** Otomatik HTML Raporlama (Extent Reports / Allure Report).
- [x] **Adım 8:** GitHub Actions entegrasyonu ile CI/CD Pipeline (Headless test çalıştırma).

---

## 🛠️ Adım 1: Ekran Görüntüsü (Screenshot) Alma & Dosya Yönetimi

Projenin ilk aşamasında, test anının kanıtını almak (Test Proof) veya hata anında ekran görüntüsü kaydetmek için dinamik bir dosya yönetim altyapısı kurgulanmıştır.

### 🔑 Öne Çıkan Özellikler & Teknik Detaylar

* **TakesScreenshot Interface:** `WebDriver` nesnesi `TakesScreenshot` arayüzüne casting yapılarak ekran görüntüsü alma yeteneği kazandırıldı.
* **Dinamik Klasör Yönetimi:** `screenshots/` dizininin varlığı `directory.exists()` ile kontrol edilir; yoksa `mkdir()` kullanılarak kod tarafında otomatik oluşturulur.
* **Dosya Çakışması Yönetimi:** `StandardCopyOption.REPLACE_EXISTING` parametresiyle, aynı isimli eski ekran görüntülerinin üzerine yazılarak gereksiz dosya birikimi engellenir.
* **Depo Temizliği (Repo Hygiene):** Otomatik üretilen test görselleri `.gitignore` kapsamına dahil edilmiştir.
* **Versiyonlama Standardı:** Commit mesajları [Conventional Commits](https://www.conventionalcommits.org/) standartlarına uygun olarak yapılandırılmaktadır.

---

## 💻 Kullanılan Teknolojiler

* **Language:** Java 17+
* **Automation Tool:** Selenium WebDriver 4.x
* **Build Tool:** Maven / Gradle
* **Browser:** Google Chrome

---

## 🚀 Projeyi Yerelde Çalıştırma

1. **Projeyi Klonlayın:**
   ```bash
   git clone https://github.com/kadersuutlu/selenium-web-automation.git
