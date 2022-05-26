--kategori tablosu
create table Category(
    ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    IMAGE_LINK VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID)
);
--visitor tablosu
CREATE TABLE Visitor(
    ID INTEGER NOT NULL,
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    MAIL VARCHAR(255) NOT NULL,
    First_Name VARCHAR(255) NOT NULL,
    Last_Name VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID),
    IS_LOGGED_IN INTEGER DEFAULT 0
);
--recipe tablosu
CREATE TABLE Recipe(
    ID INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(8000) NOT NULL,
    Date_Of_Creation DATE DEFAULT CURRENT_DATE,
    SCORE INTEGER,
    IMAGE_LINK VARCHAR(255) NOT NULL,
    PRIMARY KEY(ID),
    Category_Id INTEGER,
    constraint CategoryFK FOREIGN KEY (Category_Id) REFERENCES Category(ID),
    User_Id INTEGER
);
--recipe user baglantisi
ALTER TABLE Recipe
ADD CONSTRAINT UserFK
FOREIGN KEY (User_Id) REFERENCES visitor(ID); 




--kategori verileri ekleme
INSERT INTO CATEGORY 
VALUES (0,'CORBA', 'images/corba.jpg'); 
INSERT INTO CATEGORY 
VALUES (1,'Hamurisi', 'images/hamurisi.jpg'); 
INSERT INTO CATEGORY 
VALUES (2,'tatli', 'images/tatli.jpg'); 
INSERT INTO CATEGORY 
VALUES (3,'anayemek', 'images/anayemek.jpg'); 
INSERT INTO CATEGORY 
VALUES (4,'arasicak', 'images/arasicak.jpg'); 
INSERT INTO CATEGORY 
VALUES (5,'salata', 'images/salata.jpg'); 
<<<<<<< HEAD
=======

>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580

--kullanici verileri
INSERT INTO visitor (id, username, password, mail, first_name, last_name)
VALUES (0,'flower','password','fatihsalinmaz21@gmail.com','fatih','salinmaz'); 
INSERT INTO visitor (id, username, password, mail, first_name, last_name)
VALUES (1,'cook','password1','qwuhfas@gmail.com','zeynep','celikten');
INSERT INTO visitor (id, username, password, mail, first_name, last_name)
VALUES (2,'judge','password2','rweqasdjl@gmail.com','basar','yargici');


--tarif verileri ekleme
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, Category_Id)
VALUES (0,'Arabaşı Çorbası
','Hamurumuzu yaparak işe başlıyoruz. Tencereye üç su bardağı suyu alıp kaynamaya bırakalım. İki su bardağı su, bir su bardağından bir parmak eksik unu ve bardağın boş kalan kısmına nişasta koyalım ve homojen kıvam alana kadar karıştıralım.Kaynayan suyumuza yavaş yavaş karıştırarak ekleyelim.
Muhallebi kıvamı olana kadar karıştırarak pişirelim.
Suyla hafifçe ıslattığımız borcama hamurumuzu dökelim. Oda sıcaklığına gelince buzdolabında beş altı saat dinlendirelim.Hamurumuz dolapta dinlenirken biz tavuğumuzu haşlayalım. Tavuğumuzu haşlarken defne yaprağı ve karabiber de eklerseniz lezzeti ikiye katlarsınız. :) Tavuklarımız haşlanınca didikleyelim ve içindeki defne yaprağını çıkaralım.Tavuk suyunu çorbamızda kullanacağız. Bir tencereye zeytinyağı ve unu alıp kavuralım. Unun kokusu çıkıp tane tane ayrılıncaya kadar karıştıralım.
Salçayı ve baharatları ekleyip kavurmaya devam edelim.
Haşladığımız tavuk suyunu ekleyelim.
Didiklediğimiz tavuklarımızı da ekleyip kaynayana kadar pişirelim.
Kaynayan çorbamızı kaseye alıp servis edebiliriz. Hamurlarıda kalıpla kesip servis ettim size de fikir olsun :)
' ,'images/arabasi.jpg',0);

INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
VALUES (1,'Evde Pizza','
    Unu yoğurma kabına alalım ve ortasını havuz şeklinde açalım.
    Unun ortasına instant maya, şeker, tuz, zeytinyağı ve ılık suyu yavaş yavaş ilave ederek hamurumuzu yoğurmaya başlayalım. İnstant maya yerine yarım paket yaş mayada kullanabilirsiniz.
    Yumuşak kıvamlı ele yapışmayan bir hamur elde edelim.
    Toparlanan hamurumuzun üzerini streç film örterek yaklaşık 30 dk kadar mayalanmaya bırakalım.
    Mayalanan hamurun üzerini açalım ve un serptiğimiz tezgaha hamuru alalım.
    Elimizle yine toparlayıp havasını aldıktan sonra 2 eşit parçaya bölelim. Eğer ince hamurlu pizza hazırlamak istiyorsanız aynı hamuru 3 parçaya da bölebilirsiniz.
    Kestiğimiz parçayı beze haline getirerek un serptiğimiz tezgahta merdane yardımıyla açalım. Hafifçe un serperek merdaneye yapışmamasını sağlayabilirsiniz.
    Açtığımız hamuru pizza tepsisine almadan önce boyutu uygun mu diye kontrol edelim.
    Açtığımız hamuru unladığımız pizza tepsisine alalım ve elimizle etrafını düzeltelim.
    Dilerseniz normal fırın tepsisine pişirme kağıdı sererek veya altına mısır unu serperek de hazırlayabilirsiniz.
    Bir çatal yardımıyla hamurumuzun tüm yüzeyine delikler açalım böylelikle hamur pişerken kabarmayacaktır.
    Pizza tepsisini önceden ısıttığımız 200° fırına sürerek hamurumuzu 10 dk pişirelim.
    Hamurumuz pişerken pizzanın sosu için domates sosu, zeytinyağı, kuru fesleğen ve kekiği ekleyerek güzelce karıştıralım.
    Yarı pişen hamurumuzu fırından alıp hazırladığımız sosu üzerine bolca sürelim. Kenarlarına doğruda güzelce sürersek kenarlarıda daha güzel kızaracaktır.
    Domates sosunun üzerine bolca mozzarella peyniri rendesi veya kaşar peyniri rendesini yerleştirelim.
    Üzerine tercihe göre dilimlenmiş sucuk, dilimlenmiş sosis, domates kurusu,dilimlenmiş mantar ve mısır tanelerini ekleyelim.
    Son olarak pizzamızı tekrar fırına sürelim ve 200° fırında 20 dk daha pişmeye bırakalım.
    Peynirleri güzel bir şekilde eriyen sucukları sosisleri gayet nefis bir şekilde pişen pizzamız servise hazır. Afiyet olsun :)
','images/pizza.jpg',5,1,0); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (2,'Fırında Sütlaç','
    Fırında sütlaç için öncelikle pirinci haşlayalım. Pirinçleri güzelce yıkadıktan sonra 2 su bardağı su ile pişene kadar haşlayın. Çok az sulu kalacaktır.
    Diğer malzemeleri ekleyelim ve sütlacı pişirelim. Haşlanan pirinçlerin üzerine sütü, vanilyayı ilave edip kaynatın. Karışım kaynamaya başlayınca şekeri ve yarım su bardağı sütle karıştırdığınız 3 yemek kaşığı buğday nişastasını ilave ediniz. 10-15 dk daha kaynatıp altını kapatın.
    Sütlaçları fırına sürelim. Fırın için uygun ısıya dayanıklı sütlaç kaselerine sütlaçlarınızı paylaştırın. Tepsinize soğuk su doldurun ve sütlaç kaplarını fırın tepsinize dizin (Tepsideki su sütlaç kaplarının yarısına kadar gelecek)
    Tepsiyi fırınınızın en üst rafına yerleştirin. 180 derecede, sütlaçlarınız kızarana kadar fırınlayın. Afiyet olsun.
<<<<<<< HEAD
','images/fırında_sütlaç.jpg',3,2,0); 
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
','iimages/fırında_sütlaç.jpg',3,2,0); 
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (3,'Karnıyarık Yemeği','
    Patlıcanları çizgili soyup, yarım saat yağ çekmemesi için tuzlu suda bekletin.
    İyice yıkadıktan sonra suyunu havlu ile çektirin ve az yağda kızartın. 3 adet biberi de yağda kızartın.
    Daha sonra aynı tavada doğranmış soğanları kavurun, kıymayı ekleyerek bir müddet daha kavurun ve biberleri, sarımsağı ekleyerek 2 dakika daha kavurun.
    Küp küp doğramış olduğunuz 2 adet domatesi, tuzu, baharatları ekleyerek karıştırın.
    Üzerine bir çay bardağı su ekleyerek 5 dk kaynatın.
    Tepsiye patlıcanların ortalarını keserek yerleştirin ve bu kesiklerden patlıcanın içine bastırarak iç malzemesine yer açın ve malzeme ile patlıcanları doldurun.
    Doldurduğunuz patlıcanların üzerine ortadan ikiye kestiğiniz çeri domatesi ya da 1 adet domatesi eşit büyüklükte olacak şekilde paylaştırın ve kızarttığımız biberlerden birer tane koyun.
    Ayrı bir yerde 1 kaşık salçayı, 1 su bardağı sıcak suda ezerek patlıcanların aralarına dökün. Kıymalar çıkmasın diye üzerine dökmeyin.
    Daha sonra 170 derece de ısıttığınız fırına sürerek 20-25 dk pişirin. Dilerseniz bu işlemi pilav tenceresi gibi bir tencerede ocakta yapabilirsiniz. Aynı sürede tencerede de  pişecektir.
','images/karnıyarık.jpg',4,3,0); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (4,'Mercimek Salatası','
    Arkadaşlar önce mercimek ve arpa şehriyeyi biraz diri kalacak şekilde haşlıyoruz.
    Benim mısır konservem vardı 1. çay b. yıkayıp kullandım.
    Bir yandan önceden yıkadığımız yeşillikleri ve biberleri doğrayıp hazırlıyoruz.
    Pişen mercimek ve arpa şehriyeyi soğuduktan sonra būtūn malzemeleri yeşillikleri hepsini bir kaba alıyoruz.
    Ūzerine zeytinyağını tercihe göre baharatlarını ve limonu sıkıp tuzunu ilave edip karîştırîyoruz.
    Uygun bir tabağa salatamîzî aktarip servis ediyoruz afiyet olsun.
','images/mercimek_salatası.jpg',4,5,0); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (5,'Ezogelin Çorbası','
    Ezogelin çorbası yapmak için düdüklü tencerede önce rendelemiş olduğumuz soğanı ve ezmiş olduğumuz sarımsağı tereyağında kavuruyoruz. Soğanlar diriliğini kaybetsin yeterli yakmadan orta ateşte kavuralım.
    Soğanlar kavrulunca naneyi, kırmızı biberi ve salçayı ilave edip. Kavurmaya devam edelim.
    Bir iki karıştırdıktan sonra yıkadığımız mercimeği, pirinci, bulguru ve tuzunu da ilave ederek karıştıralım.
    Başka bir tarafta kaynamakta olan 2 litreye yakın suyu üzerine boşaltalım.
    Düdüklünün kapağını ve düdüğünü kapattıktan sonra 10 dakika pişiriyoruz. Normal tencerede de yapabilirsiniz ama biraz daha geç pişecektir (yaklaşık 30-40 dakika sürecektir).
','images/ezogelin.jpg',5,0,1); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (6,'Profiterol','İlk olarak profiterolün hamurunu hazırlayalım. Küçük bir tencereye 1 su bardağı suyu ve margarini koyarak kaynatın. Daha sonra 1 su bardağı unu ekleyerek iyice karıştırın. 2-3 dakika karışımı sürekli karıştırarak pişirin. Ocağı kapatarak 10-15 dakika hamurun soğumasını bekleyin. Hamur biraz dinlendikten sonra 3 adet yumurtayı hamura yedirmemiz gerekiyor ancak bu noktada önemli bir ayrıntı var. Yumurtaları teker teker hamura kırın ve birini iyice yedirmeden diğer yumurtayı kırmayın. Yumurtaları hamura iyice yedirdikten sonra yapışkan bir hamur elde etmiş olduk. Bu kısım biraz yorucu oluyor ama hamurun kabarması için iyice karıştırmış olmanız gerekiyor. Yumurtaları yedirdikten sonra hamuru 10 dakika dinlendirin.
 Hamurlarımızı pişirelim. Yağlanmış tepsiye, kaşık yardımı ile hamurdan ceviz büyüklüğünde parçalar alarak aralarında 2-3 cm boşluk bulunmasına dikkat ederek resimdeki gibi dökün. Elinizle şekillendirmeye çalışmayın. Daha önceden 180 derecede ısıttığımız fırına hamuru sürün. üzeri kızarana kadar yaklaşık 40 dakika pişiriyorsunuz.
Profiterol Kremasını hazırlayalım. Hamurlar piştikten sonra kremasını hazırlayın. Vanilya hariç diğer malzemeleri bir tencereye koyarak kremayı pişirin. Kremayı ocaktan aldıktan sonra vanilyasını ekleyerek karıştırın.
Kremayı profiterol hamurlarına dolduralım. Profiterolün  pişen hamurlarını ikiye bölerek ya da varsa krema sıkma torbası ile içlerini krema ile doldurun ve tepsiye dizin.
Tatlımızın üzerine çikolata sosu gezdirelim. Tüm profiterolleri doldurduktan sonra üzerine çikolata sosunu ya da benmari usulü erittiğiniz çikolatayı gezdirin.','images/profiterol.jpg',1,2,1); 
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
VALUES (7,'Fırında Levrek','Levrekleri temizletip yıkayalım.
 Fırın tepsisine yağlı kağıt sererek levrekleri üzerine yerleştirelim.
 Balıkların içine ve üzerine azar azar zeytinyağı gezdirelim. Tuz ve karabiber serpelim.
 Soğanları, domatesi ve limonu dilimleyerek balıkların içine yerleştirip fırına sürelim.
 160 derecede 45-50 dakika kadar pişirelim.','images/levrek.jpg',2,3,1); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (8,'Kıymalı Mantar Dolması','
    Mantarların saplarını kopararak İçlerine elinizle azar azar tuz sürün. Dilerseniz mantarların kabuklarını soyarak da yapabilirsiniz.
    Fırın tepsisine koyarak 20 dakika boyunca 170 derece fırında pişirin.
    Mantarlar sulanacağından tepsiye su veya yağ koymayın. Mantarların içindeki suyu ister için (çok lezzetli) ister dökün. Kürdan batırarak piştiğini kontrol edin.
    Kıymalı harç için soğanları doğrayın ve az miktarda yağ ile soteleyin. Soğanlar hafif pembeleştikten sonra içine sivri biberleri koyup sotelemeye devam edin.
    Daha sonra kıymayı ekleyerek rengi dönene kadar kavurun.
    Rengi döndükten sonra kabuklarını soyup doğradığınız domatesi, tuzunu ve baharatları ekleyin.
    Soteleme işi bitince mantarların içine hazırladığınız harcı doldurun.
    Üstüne rendelenmiş kaşar peynirini koyun.
    Sosu için salçayı çok az su ile sulandırın ve mantarların üzerine dökün.
    Ardından tekrar fırına vererek 170°C fırında 10 dakika daha kaşar peynirler eriyene kadar fırında tutun. Afiyet olun…
','images/mantar_dolması.jpg',3,4,1); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (9,'Köz Biberli Yoğurtlu Patates Salatası','
    Öncelikle patatesleri ortadan iki keselim ve bol suda haşlayalım. Ortadan kesildiğinde patatesler daha kolay haşlanacaktır.
    Çatalla haşlandığını kontrol ettikten sonra ocaktan alalım, suyunu süzdürerek ve soğumaya bırakalım.
    Kornişon turşuları küçük küçük doğrayalım.
    Közlenmiş kırmızı biberi de küp küp doğrayalım.
    Havucun kabuklarını soyalım ve rendeleyelim, taze soğan ve naneyi doğrayalım.
    Soğuyan patateslerin kabuklarını soyalım ve onları da küp küp doğrayalım.
    Geniş bir kaseye yoğurdu, mayonezi, ezilmiş sarımsakları ve tuzu alarak karıştıralım.
    Üzerine havucu, kırmızı biberi, kornişon turşuyu, mısırı, doğradığımız yeşillikleri ve patatesleri ekleyerek karıştıralım ve servis edelim. Afiyet olsun!
','images/patates_salatası.jpg',2,5,1); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (10,'Erişteli Köfteli Çorba','
    Öncelikle köfteleri hazırladıktan sonra bulamak için unu geniş bir tabağa alarak yanımıza alalım.
    Ardından köfte harcı için karıştırma kabına kıyma, köftelik bulgur, karabiber ve pul biberi alarak güzelce yoğuralım.
    Yoğurduğumuz köfte harcından elimizle küçük parçalar kopartalım ve yuvarlayarak un koyduğumuz tabağa alalım. Tüm harç bitene kadar bu işleme devam edelim.
    Şekillendirme işlemi bittikten sonra tabağımızı ileri geri sallayalım ve köftelerin tamamının una bulanmasını sağlayalım.
    Diğer taraftan; tencereye yağı koyarak rendelenmiş soğanı pembeleşinceye kadar kavuralım.
    Salçayı ekleyelim ve kaynar suyu ilave edelim. Karıştırarak kaynamaya bırakalım.
    Daha sonra köfteleri yavaş yavaş suyun içerisine ilave edelim.
    Köfteler 3-4 dakika kadar piştikten sonra içine erişte ekleyelim. Erişteler pişene kadar kaynatalım.
    Diğer tarafta çorbanın terbiyesini hazırlayalım.
    Bunun için un, yoğurt ve yumurtayı güzelce çırpalım. Ardından çorbanın suyundan bir kepçe kadarını yavaş yavaş terbiyeye ekleyerek karıştıralım.
    Terbiye ılıdıktan sonra yavaş yavaş tencereye ilave edelim ve bir yandan da karıştırmaya devam edelim.
    4-5 dakika kadar kaynattıktan sonra tuzunu ekleyip karıştırarak altını kapatalım.
    Servis etmeden önce sosu için eriyen tereyağına nane ve kırmızı toz biberi ekleyip kızdıralım, ocaktan alalım.
    Sosu çorbamızın üzerine gezdirdikten sonra servis edelim. Afiyet olsun!
','images/erişteli_köfteli_çorba.jpg',5,0,2); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (11,'Yumuşacık Mayalı Poğaça','
    Yoğurma kabına ılık su, ılık süt, yaş maya ve şekeri alıp çırpıcı ile karıştırarak mayanın erimesini sağlayalım. Mayanın daha iyi kabarması için, su ve süt karışınca, el yakmayacak düzeyde ılık olması gerekiyor.
    Üzerini streç film ile örtelim ve 10 dakika bekletelim.
    Ardından sıvı yağ, yumurta ve tuzuda ekleyerek karıştıralım.
    Unu azar azar ekleyip yoğurmaya başlayalım. Önemli olan unu az miktarlarda ekleyerek, yavaş yavaş iyice yoğurmak.
    Hamurumuzu unladığımız tezgahın üzerine alarak bir süre daha yoğuralım.
    Yoğurduğumuz hamuru tekrar yoğurma kabına alarak üzerini streçleyelim ve 1 saat kadar dinlenmeye bırakalım.
    Mayalanan hamurumuzun üzerini açıp bir kaç kez daha yoğurarak havasını çıkartalım.
    Ardından yumurta büyüklüğünde parçalar koparıp, elimizde yuvarlayalım ve  yağlı kağıt serilmiş tepsiye dizelim.
    Üzerine yumurta sarısı sürülüp, susam serptikten sonra 20 dakika kadar daha bekletelim.
    Daha sonra 180 derece fırında pişmeye bırakalım. Pişme süresi fırına göre değişebilir ama 20-25 dakika kadar bir sürede pişecektir. Ben bu ölçülerle 16 adet poğaça elde ettim.
    Puf puf kabaran, yumuşacık poğaçalarımız hazır. İster kahvaltılarda ekmek yerine yapabilirsiniz ya da çocuklar için arasına peynir, domates gibi kahvaltılıklar ekleyerek de, sandviç gibi servis edebilirsiniz. Mini mini sandviçler hazırlayabileceğiniz, kolay ve lezzetli poğaça tarifimizi çok beğeneceksiniz. Arasına arzunuza söre malzemelerle sandviçler hazırlayabileceğiz, kahvaltılık poğaça tarifini mutlaka denemenizi tavsiye ederim.
','images/pamuk-poğaça.jpg',4,1,2); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (12,'Pratik Fit Brownie','Yumurta ve balı köpürene kadar çırpıyoruz.
Ardından içine kabartma tozu, kakao ve sütü ekleyip, karıştırıyoruz.
Daha sonra unuda ekleyip, kıvam alana kadar karıştırıyoruz.
İçine fındık ve çikolatayı katıp, homejen şekilde karışınca yağlı kağıt serili borcama döküyoruz.
Üstüne fındık ve çikolatayı ekleyip, önceden ısıtılmış 180 derece fırında 10-15 dakika pişiriyoruz. Afiyet olsun','images/fit-brownie.jpg',3,2,2); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (13,'Tavuklu Sultan Kebabı','
    Patlıcanı alacalı soyarak küçük küçük doğrayın ve sıvı yağ ile kızartın.
    Süzgece alarak yağının süzmesi bekleyin bu arada tavukları soteleyin.
    Tavukları kuş başı doğrayın, tencereye alarak arada karıştırarak pişirin.
    Rengi dönüp suyunu saldığında yemeklik doğranmış soğanı ekleyin.
    Soğanlar yumuşadıktan sonra salçayı ve isteğe bağlı kullanacaksanız eğer ketçabı ilave edin.
    Kapağını kapatarak 4-5 dk. kadar pişmeye bırakın.
    Suyunu çektikten sonra bezelye ve patlıcanı ilave edin.
    Baharatları da ekleyerek 3-4 dk. daha pişirin.
    Tavuklar da artık pişmiş olmalılar.
    Beşamel sos için, tereyağını eritin ve unu kokusu çıkana kadar kavurun.
    Üzerine sütü ilave ederek çırpıcı yardımı ile topak kalmayacak şekilde koyulaşıp göz göz olana kadar karıştırarak pişirin.
    Son olarak tuzunu ilave ederek ocaktan alın.
    Küçük bir kaseye 4’e böldüğünüz yufkadan serin.
    Yufkanın kenarlarından parçalar kopartarak çukur olan kısma bir kat daha serin.
    Kaseye tavuklu harçtan koyarak, kasenin kenarlarından sarkan yufkaları üzerine kapatın.
    Yağlanmış fırın kabına ters çevirin.
    Tüm malzemeyi bu şekilde yaptıktan sonra üzerleri beşamel sostan her birine eşit miktarda olacak şekilde dökün.
    190 derece fırında yufkalar pembeleşene kadar pişirin.
    Sonra üzerine kaşar peyniri rendesi serpin ve tekrar fırına sürün.
    Peynirler eriyip kızardığında alabilirsiniz.
','images/tavuklu-sultan-kebabı.jpg',4,3,2); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (14,'Patlıcan Balığı','Patlıcanları şerit halinde doğrayıp tuzlu suda biraz yumuşayana kadar haşlayın.
Önce un sonra yumurta son olarak da Mısır ununa (isterseniz galeta da olur) baharatlı isterseniz mısır ununa baharat da ekleyip bulayıp kızmış yağda kızartın.
Üzerine de sarımsaklı yoğurt döküp servis edin.','images/patlıcan.jpg',4,4,2); 
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
VALUES (15,'Pastırmalı Humus','
    Nohutları rondo da püre haline getirdikten sonra kalan malzemeleri ilave edin ve el blenderıyla pürüzsüz kıvam alana dek ezin.
    Kıvamı koyu gelirse bir miktar su ilave edebilirsiniz.
    Üzerine bolca tereyağında pişirilmiş pastırma dilimleri ekleyerek servis edin. Ben çam fıstığı da serptim.
','images/pastırmalı_humus.jpg',2,4,0); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (16,'Ev Yapımı Lahmacun','
    Hamur için ılık su, süt ve şekeri yoğurma kabına alıyoruz.
    Ardından mayayı da ilave ederek, mayanın erimesi için 5-10 dk bekletiyoruz.
    Yağ, tuz ve yavaş un ilavesiyle, hamuru yumuşak olmayacak ve ele yapışmayacak şekilde yoğuruyoruz.
    Üzerini kapatarak 1 saat mayalanmaya bırakıyoruz.
    Hamur mayalanırken bizde ayrı kapta lahmacunun kıymalı harcını hazırlıyoruz.
    Kıymamızın üzerine, çok ince kıydığımız soğan, biber ve sarımsağı koyuyoruz.
    Ayrı bir kaba domatesi rendeliyoruz ve üzerine 2 yemek kaşığı biber salçamızı ilave edip, çok iyi bir şekilde karıştırıyoruz.
    Ve bu sosu da kıymalı harcımızın üzerine boşaltıyoruz.
    En son yağını, baharatlarını ve tuzunu ilave edip, çok iyi yoğuruyoruz.
    Hamur mayalandıktan sonra avuç içi kadar bezelere ayırıyoruz.
    Bezelerin altına üzerine un serperek incecik açıyoruz.
    Üzerine 2 kaşık kadar kıymalı harcı yayıyoruz ve ısıtılmış 200 derece fırında yaklaşık 5-6 dk pişiriyoruz.
    Yada yanmaz tava veya pizza tavasında, üzerine kapak örterek 5 dk kadar pişiriyoruz. Hazırlarını aratmayacak kadar lezzetli:)
    Şimdiden afiyet olsun:)
','images/ev_yapımı_lahmacun.jpg',1,1,1); 
<<<<<<< HEAD
INSERT INTO RECIPE (ID, RECIPENAME, DESCRIPTION, IMAGELINK, SCORE,CATEGORYNO,USERID)
=======
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
>>>>>>> 8df2aa23ab4a640150c6954e3ad2bbe694d7a580
VALUES (17,'Arpa Şehriye Salatası','Öncelikle tencerenin içine şehriye konulup pembeleşinceye kadar sıvı yağ ile şehriyelerin rengi dönene kadar kavrulur.
Kavrulan şehriyenin üzerini geçecek kadar ılık su ve tuz ilave edip karıştırılır,
Tencerenin kapağı kapatılır, şehriyeler yumuşayıp göz göz olana kadar orta ateşte pilav gibi pişirilir.
Suyunu çekip pişen şehriyelerin üzerine kağıt havlu kapatılarak dinlenmeye ve soğumaya bırakılır.
Ardından diğer malzemeler ufak ufak doğranıp hazırlanır. (havuç rende olmalı)
Soğuyan şehriye derin bir kaba boşaltılır, doğrayıp hazırlanan malzemeler içine ilave edilir.
1 tatlı kaşığı nar ekşisi, yarım limon suyu ve tuzunu ilave edip nazikçe karıştırarak dilediğiniz gibi servis yapabilirsiniz. Şimdiden afiyetler olsun ','images/arpa_salata.jpg',2,5,2); 
INSERT INTO RECIPE (ID, name, DESCRIPTION, IMAGE_LINK, SCORE,Category_Id,User_Id)
VALUES (18,'Beyti Kebabı','
    İlk olarak beytimizin köftesini hazırlıyoruz. Soğan, sarımsak ve yumurtayı rondoya alarak çekelim. Rondo kullanmak istemezseniz soğan ve sarımsağı rendeleyebilirsiniz.
    Köfte için rondoda çektiğimiz malzemeleri, kıyma, galeta unu ve baharatları derince bir kaba alarak köfte harcını güzelce yoğuralım.
    Köfte harcından bir miktar alarak elimizde uzun ve yassı bir şekilde biçimlendirelim. Unutmayın köfteler pişerken kısalıp kalınlaşacaktır, eğer çok miktarda çıkartmak isterseniz köftelerinizi daha ince yapabilirsiniz.
    Şekillendirdiğimiz köfteleri pişirme kağıdı serdiğiniz fırın tepsisine yerleştelim.
    Önceden ısıtılmış 190 derece fırında köftelerimizi 15 dakika pişirelim.
    Köftelerin pişmesine yakın, tereyağını eritelim.
    Yufkalardan bir tanesini alarak ikiye keselim.
    Tek kat olacak şekilde bir parçasının üzerine tereyağı sürelim.
    Fırından aldığımız köfteleri yufkanın geniş kısmına boşluk bırakmadan tek sıra halinde dizelim ve yufkanın kenarlarını katlayarak rulo şeklinde saralım. (Fotoğraflarda ve videoda bu bölümü görebilirsiniz)
    Tüm malzemeleri sardıktan sonra ruloları verev olarak şekilde keselim ve tekrar tepsiye dizelim.
    Üzerlerine tereyağı sürerek fırına sürelim.
    Kebabımız fırında kızarırken domates sosunu hazırlayalım. Bunun için tereyağını tavada eritelim, domates rendesini ekleyelim. Birkaç dakika kaynadıktan sonra karabiber ve tuz ilave edelim. Kıvamı koyu ise bir miktar su ilave edebilirsiniz.
    Yufkaların üzeri hafifçe kızardıktan sonra fırından alalım.
    Beyti kebabımızı servis tabağına aldıktan sonra üzerine domates sosundan gezdirelim, yanına birkaç kaşık yoğurt koyalım.
    Sıcak sıcak servis edelim :)
','images/beyti.jpg',5,3,1);
--// TODO: Select top 5 randrom recipe
SELECT *
FROM recipe
Where score>=4
ORDER BY score DESC
FETCH FIRST 5 ROWS ONLY;

--// TODO Select top 10 latest recipe
SELECT *
FROM recipe
ORDER BY date_of_creation DESC
FETCH FIRST 10 ROWS ONLY;

