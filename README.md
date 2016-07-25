# did-you-mean-service

**Programın çalışması için 'words.txt'yi "C:\\" ye koymanız gerekmekte.**

'Did you mean' servisi için kullanılabilecek başlıca algoritmalar Levenshtein, Jaro-Winkler ve n-gram'dır. Ben bu programda Levenshtein algoritmasını daha gelişmişi olan Damerau-Levenshtein algorithmasını kullandım.

Jaro-Winkler algoritması kelimeler arasındaki eşleşen harflerin sayısına ve bunların yerlerine, sıralarına bağlı olarak 0 ila 1 arasında bir oran bulur. Bu oranın yüksekliği kelimelerin benzerliğini gösterir.

n-gram bir dizinin sonraki elemanını öncekilerden edinilen istatistiklerle bulmaya yarayan bir algoritmadır.

Levensthein algoritmasıysa iki kelime arasındaki uzaklığı hesaplar. 'abc' kelimesinin 'abd' kelimesine uzaklığı 1'dir çünkü sadece 3. harfi değiştirerek öbür kelimeye ulaşılabilir. Levenshtein harf ekleme, çıkarma veya değiştirme işlemlerine bakar ve bir kelimeden öbür kelimeyi türetmek için bu işlemlerden ne kadar gerekirse bu sayı bize iki kelimenin uzaklığını verir.

Benim bu programda kullandığım Damerau-Levenshtein algoritması ekstra olarak yanyana olan harflerin yanlış yazımına da bakmakta. Örneğin, 'ABACUS' ve 'AABCUS' kelimelerinin uzaklığı 1'dir çünkü 2. ve 3. harfin yeri değiştirilebilir, fakat bu kural sadece yanyana olan harfler için geçerlidir.

##Programın İşleyişi

Main fonksiyonu 'StartingPoint' classında bulunmaktadır. Öncelikle program kullanıcıdan bir kelime girdisi ister ve bunu 'searchInput' stringine atar. Daha sonra 'DidYouMeanService' classından 'DidYouMeanList' fonksiyonunu çağırır.

'DidYouMeanList' fonksiyonu geriye bir ArrayList döndürmektedir ve bu liste girdiye benzer kelimeleri döndürecektir. Fonksiyon çağrıldığında 'words.txt' dosyasını satır satır okumaya başlar. Her satır için şu işlemleri yapar:

1. Kullanıcı girdisi ile dosyadaki kelimenin uzunluklarını karşılaştır. Eğer uzunluk farkı 1'den büyükse, bu iki kelime aynı veya bizim kriterlerimizde benzer olamaz. Bu sebeple daha sonraki işlemlerle boşa vakit harcamak yerine dosyadaki bir sonraki kelimeye atlanır böylece zamandan kazanılmış olur. Eğer uzunluk farkı 0 veya 1'se bu iki kelime benzer olabilir bu durumda da 2. adıma geçilir.

2. Girdi ile dosyadaki kelime aynı kelimeler ise buradan sonra algoritmayı çalıştırmanın anlamı yok çünkü, 'Did you mean' servisine gerek yok. Eğer kelimeler aynı ise program girdiyi ArrayList'in son elemanı olarak ekliyor ve 'StartingPoint' classına geri dönüyor. Burada son eleman girdiyle karşılaştırılacak ve kelimenin dosyada bulunduğu anlaşılacak.

3. Eğer kelimeler de aynı değilse iki kelimenin uzaklığı hesaplanacak. Bunun için Damerau-Levenshtein algoritmasını içeren 'DistanceCalculator' classı çağırılıyor.

4. Uzaklığı sadece 1 işlem olan kelimeler ArrayList'e atılıyor.

5. Bir sonraki kelime için başa dönülüyor.

Dosyadaki her kelimeye baktıktan sonra, 'StartingPoint' classına geri dönülür, burada ilk önce ArrayList boş mu değil mi ona bakılır, eğer boşsa hiç benzer kelime bulunamamıştır. Daha sonra ArrayList'in son elemanına bakılır çünkü bu eleman kullanıcı girdisiyle aynıysa demek ki girdi listede bulunmuş demektir, 'did you mean' servisine gerek yoktur. Program bunu da atlarsa ArrayList'in elemanları tek tek basılır.

##Damerau-Levenshtein Algoritması

Algoritma programda 'DistanceCalculator' classının içinde bulunmakta. Uzaklığı bulabilmek için iki kelimenin boyutlarından birer fazla olan iki boyutlu bir matrise ihtiyacımız var. 

Matrisin ilk satırı ve ilk sütunu 0'dan kelimelerin boyu olan sayıya kadar sırayla dolduruluyor. (0, 1, 2, 3..)

Buradan sonra matristeki her boşluğu doldurmak gerek. Bu boşlukları doldurmanın bir kuralı var. Boşluğun koordinatının (i,j) olduğunu varsayalım. Boşluğa aşağıdakilerin en küçüğü konmakta.

1. (i-1,j) + 1

2. (i, j-1) + 1

3. (i-1, j-1) + 0,1 : Eğer o anda bakılan iki kelimedeki harfler aynıysa (match) 0 ile fakat aynı değilse (mismatch) 1 ile toplanır. 

4. (i-2, j-2) + 1 : Bunun olması için i ve j 1'den büyük olmalı ve iki kelimede de yanyana harfler aynı olmalı. ( 'abACus', 'abCAus')

##Örnek Çıktılar

1.Verilen 'ABACUSS' girdisi için gelen sonuçlar.

![abacuss](https://github.com/brkyataman/did-you-mean-service/blob/master/did-you-mean-abacus.PNG)

2.Verilen 'ABACSU' girdisi için gelen sonuçlar. Burada program son iki harfteki hatayı algılıyor.

![abacsu](https://github.com/brkyataman/did-you-mean-service/blob/master/did-you-mean-abacsu.PNG)

3.Verilen 'pollini' girdisi için gelen sonuçlar.

![pollini](https://github.com/brkyataman/did-you-mean-service/blob/master/did-you-mean-pollini.PNG)

4.Zaten dosyada olan bir değer olan 'POLLEX' verilince.

![pollex](https://github.com/brkyataman/did-you-mean-service/blob/master/pollex.PNG)
