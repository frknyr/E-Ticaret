# E-Ticaret

- Spring framework ile geliştirmiş olduğum e-ticaret altyapısı

#### Projeyi Geliştirirken Kullandığım Yazılım Prensibleri
- Projeyi geliştirme aşamasında katmanlı mimariler kullandım. Oluşturduğum business, dataAccess, entities, core ve webApi paketleri ile kodun okunurluğunu kolaylaştırmak ve projenin değişime karşı göstermiş olduğu direnci en aza indirgemeye çalıştım.


- Paketler veya sınıflar arasındaki iletişimi interfaceler ile sağlayarak projenin değişime karşı direncini azaltmaya çalıştım.


- Veritabanı nesneleri arasındaki ilişkiyi çeşitli anotasyonlar (@ManyToOne vb.) kullanarak sağladım

- IoC prensibini uygulayarak nesneleri her seferinde oluşturmak yerine bir kez oluşturup IoC konteynırına yerleştirip ihtiyaç olunan yerlerde nesneleri kullanılabilir hale getirdim. Bu şekilde yazılımın performansı arttı hemde değişime karşı göstermiş olduğu direnç azaldı.

- Response ve request prensibini uygulamaya çalıştım. Her kullanıcı sorgusu için ayrı bir response sınıfı oluşturdum. Bu şekilde kullanıcıya istediğimiz veriyi istediğimiz bir şekilde gösterebilmeyi amaçladım. Örnek verecek olursak, elimizdeki product nesnesinin unitPrice değerini her seferinde kullanıcıya göstermek istemeyebiliriz. Bütün ürünleri gösterirken fiyat değerini göstermek istemeyebiliriz, sadece bir  ürünü gösterirken fiyat değerini göstermek isteyebiliriz. Bu tür durumlarda kolaylık sağlaması için her durum için ayrı ayrı response sınıfı oluşturdum.

- İş kurallarını manager sınıflarına yazmak yerine business paketinin içine oluşturduğum rules paketinin içerisine yazarak istenilen yerlerde kullanılmasını sağladım. Bu sayede kod tekrarı yapmadım, iş kurallarındaki değişimi kolaylaştırdım ve manager sınıflarının okunurluluğunu arttırdım.

- Her veritabanı nesnesi için ayrı bir repository oluşturarak veritabanı işlemlerini kolaylıkla gerçekleştirdim.
