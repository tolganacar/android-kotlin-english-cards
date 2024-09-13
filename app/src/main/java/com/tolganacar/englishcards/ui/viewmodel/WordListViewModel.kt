package com.tolganacar.englishcards.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolganacar.englishcards.R
import com.tolganacar.englishcards.data.model.EnglishWords

class WordListViewModel : ViewModel() {

    private val _words = MutableLiveData<List<EnglishWords>?>()
    val words: MutableLiveData<List<EnglishWords>?> get() = _words

    init {
        loadWords()
    }

    private fun loadWords() {
        val wordList = listOf(
            EnglishWords("Table", "ˈteɪbəl", R.drawable.table, "Masa", "I put the book on the table.", "Kitabı masanın üzerine koydum.", "A1") ,
            EnglishWords("Chair", "tʃɛər", R.drawable.chair, "Sandalye", "She is sitting on the chair.", "O sandalyede oturuyor.", "A1"),
            EnglishWords("Book", "bʊk", R.drawable.book, "Kitap", "I am reading a book.", "Bir kitap okuyorum.", "A1"),
            EnglishWords("Pen", "pɛn", R.drawable.pen, "Kalem", "This is a blue pen.", "Bu bir mavi kalemdir.", "A1"),
            EnglishWords("Car", "kɑr", R.drawable.car, "Araba", "I have a new car.", "Yeni bir arabam var.", "A1"),
            EnglishWords("Dog", "dɔg", R.drawable.dog, "Köpek", "The dog is barking.", "Köpek havlıyor.", "A1"),
            EnglishWords("Cat", "kæt", R.drawable.cat, "Kedi", "The cat is sleeping.", "Kedi uyuyor.", "A1"),
            EnglishWords("Window", "ˈwɪndoʊ", R.drawable.window, "Pencere", "She opened the window.", "O pencereyi açtı.", "A1"),
            EnglishWords("House", "haʊs", R.drawable.house, "Ev", "They live in a big house.", "Onlar büyük bir evde yaşıyorlar.", "A1"),
            EnglishWords("Bike", "baɪk", R.drawable.bike, "Bisiklet", "He rides his bike to school.", "O bisikletiyle okula gidiyor.", "A1"),
            EnglishWords("Computer", "kəmˈpjuːtər", R.drawable.computer, "Bilgisayar", "I use a computer for work.", "İş için bilgisayar kullanırım.", "A2"),
            EnglishWords("Phone", "foʊn", R.drawable.phone, "Telefon", "He called me on the phone.", "O beni telefondan aradı.", "A2"),
            EnglishWords("Television", "ˈtɛlɪˌvɪʒən", R.drawable.television, "Televizyon", "We watch television in the evening.", "Akşamları televizyon izleriz.", "A2"),
            EnglishWords("Lamp", "læmp", R.drawable.lamp, "Lamba", "She turned on the lamp.", "O lambayı açtı.", "A2"),
            EnglishWords("Bed", "bɛd", R.drawable.bed, "Yatak", "I go to bed early.", "Erken yatağa giderim.", "A2"),
            EnglishWords("Watch", "wɒtʃ", R.drawable.watch, "Saat", "This is my new watch.", "Bu benim yeni saatim.", "A2"),
            EnglishWords("Fridge", "frɪdʒ", R.drawable.fridge, "Buzdolabı", "The milk is in the fridge.", "Süt buzdolabında.", "A2"),
            EnglishWords("Bag", "bæg", R.drawable.bag, "Çanta", "She carries a red bag.", "O kırmızı bir çanta taşıyor.", "A2"),
            EnglishWords("Glass", "glæs", R.drawable.glass, "Bardak", "Can I have a glass of water?", "Bir bardak su alabilir miyim?", "A2"),
            EnglishWords("Bus", "bʌs", R.drawable.bus, "Otobüs", "I take the bus to school.", "Okula otobüsle giderim.", "A2"),
            EnglishWords("Camera", "ˈkæmərə", R.drawable.camera, "Kamera", "I bought a new camera.", "Yeni bir kamera aldım.", "B1"),
            EnglishWords("Printer", "ˈprɪntər", R.drawable.printer, "Yazıcı", "The printer is out of paper.", "Yazıcının kağıdı bitti.", "B1"),
            EnglishWords("Microwave", "ˈmaɪkrəˌweɪv", R.drawable.microwave, "Mikrodalga", "Heat the food in the microwave.", "Yemeği mikrodalgada ısıt.", "B1"),
            EnglishWords("Umbrella", "ʌmˈbrɛlə", R.drawable.umbrella, "Şemsiye", "Don't forget your umbrella.", "Şemsiyeni unutma.", "B1"),
            EnglishWords("Suitcase", "ˈsuːtkeɪs", R.drawable.suitcase, "Bavul", "Pack your suitcase for the trip.", "Gezi için bavulunu hazırla.", "B1"),
            EnglishWords("Helmet", "ˈhɛlmɪt", R.drawable.helmet, "Kask", "You must wear a helmet on the bike.", "Bisiklette kask takmalısın.", "B1"),
            EnglishWords("Ticket", "ˈtɪkɪt", R.drawable.ticket, "Bilet", "I bought a movie ticket.", "Bir sinema bileti aldım.", "B1"),
            EnglishWords("Backpack", "ˈbækˌpæk", R.drawable.backpack, "Sırt Çantası", "He carries a heavy backpack.", "O ağır bir sırt çantası taşıyor.", "B1"),
            EnglishWords("Headphones", "ˈhɛdfoʊnz", R.drawable.headphones, "Kulaklık", "I listen to music with my headphones.", "Müziği kulaklıkla dinlerim.", "B1"),
            EnglishWords("Keyboard", "ˈkiːbɔːrd", R.drawable.keyboard, "Klavye", "This is a wireless keyboard.", "Bu bir kablosuz klavyedir.", "B1"),
            EnglishWords("Projector", "prəˈʤɛktər", R.drawable.projector, "Projeksiyon", "We use a projector in meetings.", "Toplantılarda projeksiyon kullanıyoruz.", "B2"),
            EnglishWords("Briefcase", "ˈbriːfˌkeɪs", R.drawable.briefcase, "Evrak Çantası", "He brought his briefcase to the office.", "Ofise evrak çantasını getirdi.", "B2"),
            EnglishWords("Dishwasher", "ˈdɪʃˌwɒʃər", R.drawable.dishwasher, "Bulaşık Makinesi", "The dishwasher is full.", "Bulaşık makinesi dolu.", "B2"),
            EnglishWords("Air Conditioner", "ɛr kənˈdɪʃənər", R.drawable.air_conditioner, "Klima", "Turn on the air conditioner, it's hot.", "Klimaları aç, çok sıcak.", "B2"),
            EnglishWords("Thermometer", "θərˈmɒmɪtər", R.drawable.thermometer, "Termometre", "Check the temperature with a thermometer.", "Termometre ile sıcaklığı kontrol et.", "B2"),
            EnglishWords("Vacuum Cleaner", "ˈvækjuəm ˈkliːnər", R.drawable.vacuum_cleaner, "Elektrik Süpürgesi", "I vacuum the house every weekend.", "Her hafta sonu evi süpürürüm.", "B2"),
            EnglishWords("Stethoscope", "ˈstɛθəˌskoʊp", R.drawable.stethoscope, "Stetoskop", "The doctor used a stethoscope.", "Doktor stetoskop kullandı.", "B2"),
            EnglishWords("Calculator", "ˈkælkjəˌleɪtər", R.drawable.calculator, "Hesap Makinesi", "He uses a calculator for math.", "Matematik için hesap makinesi kullanıyor.", "B2"),
            EnglishWords("Scissors", "ˈsɪzərz", R.drawable.scissors, "Makas", "I need a pair of scissors.", "Bir makasa ihtiyacım var.", "B2"),
            EnglishWords("Fan", "fæn", R.drawable.fan, "Vantilatör", "The fan is keeping the room cool.", "Vantilatör odayı serin tutuyor.", "B2"),
            EnglishWords("Microscope", "ˈmaɪkrəˌskoʊp", R.drawable.microscope, "Mikroskop", "We observed the cells under a microscope.", "Hücreleri mikroskop altında inceledik.", "C1"),
            EnglishWords("Telescope", "ˈtɛləˌskoʊp", R.drawable.telescope, "Teleskop", "He looked at the stars with a telescope.", "Teleskopla yıldızlara baktı.", "C1"),
            EnglishWords("Typewriter", "ˈtaɪpraɪtər", R.drawable.typewriter, "Daktilo", "She typed the letter on a typewriter.", "Mektubu daktiloda yazdı.", "C1"),
            EnglishWords("Gavel", "ˈgævəl", R.drawable.gavel, "Tokmak", "The judge banged the gavel.", "Hakim tokmağı vurdu.", "C1"),
            EnglishWords("Syringe", "sɪˈrɪndʒ", R.drawable.syringe, "Enjektör", "The nurse used a syringe for the injection.", "Hemşire enjeksiyon için enjektör kullandı.", "C1"),
            EnglishWords("Anvil", "ˈænvɪl", R.drawable.anvil, "Örs", "The blacksmith used an anvil.", "Demirci bir örs kullandı.", "C1"),
            EnglishWords("Barometer", "bəˈrɒmɪtər", R.drawable.barometer, "Barometre", "The barometer showed a drop in pressure.", "Barometre basınçta bir düşüş gösterdi.", "C1"),
            EnglishWords("Monocle", "ˈmɒnəkəl", R.drawable.monocle, "Tek Gözlük", "He wore a monocle to read.", "Okumak için tek gözlük taktı.", "C1"),
            EnglishWords("Globe", "gloʊb", R.drawable.globe, "Küre", "She spun the globe to find a country.", "Bir ülke bulmak için küreyi çevirdi.", "C1"),
            EnglishWords("Pendulum", "ˈpɛndʒələm", R.drawable.pendulum, "Sarkaç", "The pendulum swung back and forth.", "Sarkaç ileri geri sallandı.", "C1"),
            EnglishWords("Astrolabe", "ˈæstrəˌleɪb", R.drawable.astrolabe, "Astrolab", "The navigator used an astrolabe.", "Seyirci bir astrolab kullandı.", "C2"),
            EnglishWords("Sextant", "ˈsɛkstənt", R.drawable.sextant, "Seksan", "He measured the angle with a sextant.", "Seksan ile açıyı ölçtü.", "C2"),
            EnglishWords("Spectrometer", "spɛkˈtrɒmɪtər", R.drawable.spectrometer, "Spektrometre", "They analyzed light with a spectrometer.", "Işığı spektrometre ile analiz ettiler.", "C2"),
            EnglishWords("Oscilloscope", "əˈsɪləˌskoʊp", R.drawable.oscilloscope, "Osiloskop", "We observed the waveforms on the oscilloscope.", "Osiloskopta dalga formlarını gözlemledik.", "C2"),
            EnglishWords("Thermostat", "ˈθɜːrməˌstæt", R.drawable.thermostat, "Termostat", "Adjust the thermostat to 22 degrees.", "Termostatı 22 dereceye ayarla.", "C2"),
            EnglishWords("Altimeter", "ˈæltɪˌmiːtər", R.drawable.altimeter, "Altimetre", "The pilot checked the altitude with the altimeter.", "Pilot altitudu altimetre ile kontrol etti.", "C2"),
            EnglishWords("Chronometer", "krəˈnɒmɪtər", R.drawable.chronometer, "Kronometre", "He used a chronometer to track time.", "Zamanı izlemek için bir kronometre kullandı.", "C2"),
            EnglishWords("Gyroscope", "ˈʤaɪrəˌskoʊp", R.drawable.gyroscope, "Jiroskop", "The gyroscope helps stabilize the ship.", "Jiroskop gemiyi stabilize etmeye yardımcı olur.", "C2"),
            EnglishWords("Dynamometer", "daɪnəˈmɒmɪtər", R.drawable.dynamometer, "Dinamometre", "The dynamometer measured the force.", "Dinamometre kuvveti ölçtü.", "C2")
        )
        _words.value = wordList.shuffled()
    }

    fun refreshWords() {
        _words.value = _words.value?.shuffled()
    }
}
