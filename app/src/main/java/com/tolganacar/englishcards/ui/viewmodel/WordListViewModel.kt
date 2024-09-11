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
            EnglishWords("Apple", "ˈæpəl", R.drawable.apple, "Elma", "I eat an apple every day.", "Her gün bir elma yerim."),
            EnglishWords("Book", "bʊk", R.drawable.book, "Kitap", "I read a book last night.", "Dün gece bir kitap okudum."),
            EnglishWords("Car", "kɑr", R.drawable.car, "Araba", "He drives a fast car.", "Hızlı bir araba sürüyor."),
            EnglishWords("Dog", "dɔg", R.drawable.dog, "Köpek", "The dog is barking loudly.", "Köpek yüksek sesle havlıyor."),
            EnglishWords("Elephant", "ˈɛlɪfənt", R.drawable.elephant, "Fil", "The elephant is a large animal.", "Fil büyük bir hayvandır."),
            EnglishWords("Flower", "ˈflaʊər", R.drawable.flower, "Çiçek", "The flower is blooming.", "Çiçek açıyor."),
            EnglishWords("Guitar", "gɪˈtɑr", R.drawable.guitar, "Gitar", "She plays the guitar beautifully.", "Gitarı çok güzel çalıyor."),
            EnglishWords("House", "haʊs", R.drawable.apple, "Ev", "They bought a new house.", "Yeni bir ev aldılar."),
            EnglishWords("Ice", "aɪs", R.drawable.apple, "Buz", "The ice is melting.", "Buz eriyor."),
            EnglishWords("Juice", "ʤus", R.drawable.apple, "Meyve suyu", "I drink orange juice in the morning.", "Sabahları portakal suyu içerim."),
            EnglishWords("Kite", "kaɪt", R.drawable.apple, "Uçurtma", "The kite is flying in the sky.", "Uçurtma gökyüzünde uçuyor."),
            EnglishWords("Lion", "ˈlaɪən", R.drawable.apple, "Aslan", "The lion is the king of the jungle.", "Aslan ormanın kralıdır."),
            EnglishWords("Moon", "mun", R.drawable.apple, "Ay", "The moon is full tonight.", "Bu gece ay dolunay."),
            EnglishWords("Nest", "nɛst", R.drawable.apple, "Yuva", "The bird built a nest in the tree.", "Kuş ağaçta bir yuva yaptı."),
            EnglishWords("Orange", "ˈɔrɪndʒ", R.drawable.apple, "Portakal", "I peel an orange for breakfast.", "Kahvaltıda bir portakal soyuyorum."),
            EnglishWords("Pen", "pɛn", R.drawable.apple, "Kalem", "I write with a blue pen.", "Mavi bir kalemle yazıyorum."),
            EnglishWords("Queen", "kwin", R.drawable.apple, "Kraliçe", "The queen ruled for many years.", "Kraliçe yıllarca hüküm sürdü."),
            EnglishWords("Rainbow", "ˈreɪnboʊ", R.drawable.apple, "Gökkuşağı", "A beautiful rainbow appeared after the rain.", "Yağmurdan sonra güzel bir gökkuşağı çıktı."),
            EnglishWords("Sun", "sʌn", R.drawable.apple, "Güneş", "The sun is shining brightly.", "Güneş parlak bir şekilde parlıyor."),
            EnglishWords("Tree", "tri", R.drawable.apple, "Ağaç", "The tree is tall and strong.", "Ağaç uzun ve güçlü."),
            EnglishWords("Umbrella", "ʌmˈbrɛlə", R.drawable.apple, "Şemsiye", "I use an umbrella when it rains.", "Yağmur yağdığında şemsiye kullanırım."),
            EnglishWords("Vase", "veɪs", R.drawable.apple, "Vazo", "The vase is filled with flowers.", "Vazo çiçeklerle dolu."),
            EnglishWords("Whale", "weɪl", R.drawable.apple, "Balina", "The whale is swimming in the ocean.", "Balina okyanusta yüzüyor."),
            EnglishWords("Yacht", "jɒt", R.drawable.apple, "Yat", "They are sailing on a yacht.", "Yatta seyahat ediyorlar."),
            EnglishWords("Airplane", "ˈɛrˌpleɪn", R.drawable.apple, "Uçak", "The airplane is ready for takeoff.", "Uçak kalkışa hazır."),
            EnglishWords("Ball", "bɔl", R.drawable.apple, "Top", "He kicked the ball into the goal.", "Topu kaleye attı."),
            EnglishWords("Cat", "kæt", R.drawable.apple, "Kedi", "The cat is sleeping on the couch.", "Kedi kanepede uyuyor."),
            EnglishWords("Door", "dɔr", R.drawable.apple, "Kapı", "Please close the door.", "Lütfen kapıyı kapat."),
            EnglishWords("Eagle", "ˈiɡəl", R.drawable.apple, "Kartal", "The eagle soars high in the sky.", "Kartal gökyüzünde süzülüyor."),
            EnglishWords("Fish", "fɪʃ", R.drawable.apple, "Balık", "He caught a big fish.", "Büyük bir balık yakaladı."),
            EnglishWords("Giraffe", "ʤəˈræf", R.drawable.apple, "Zürafa", "The giraffe has a long neck.", "Zürafanın uzun bir boynu vardır."),
            EnglishWords("Hat", "hæt", R.drawable.apple, "Şapka", "She wears a hat in the sun.", "Güneşte şapka takıyor."),
            EnglishWords("Igloo", "ˈɪɡlu", R.drawable.apple, "Iglo", "They built an igloo out of snow.", "Kardan bir iglo yaptılar."),
            EnglishWords("Jacket", "ˈʤækɪt", R.drawable.apple, "Ceket", "He wore a warm jacket.", "Sıcak bir ceket giydi."),
            EnglishWords("Kangaroo", "ˌkæŋɡəˈru", R.drawable.apple, "Kanguru", "The kangaroo jumps high.", "Kanguru yüksek zıplıyor."),
            EnglishWords("Lamp", "læmp", R.drawable.apple, "Lamba", "The lamp is on the table.", "Lamba masanın üzerinde."),
            EnglishWords("Mouse", "maʊs", R.drawable.apple, "Fare", "The mouse ran across the room.", "Fare odanın içinde koştu."),
            EnglishWords("Owl", "aʊl", R.drawable.apple, "Baykuş", "The owl hoots at night.", "Baykuş gece öter."),
            EnglishWords("Pencil", "ˈpɛnsəl", R.drawable.apple, "Kurşun kalem", "She writes with a pencil.", "O, kurşun kalemle yazar."),
            EnglishWords("River", "ˈrɪvər", R.drawable.apple, "Nehir", "The river flows through the valley.", "Nehir vadinin içinden akar."),
            EnglishWords("Star", "stɑr", R.drawable.apple, "Yıldız", "The stars are twinkling in the sky.", "Yıldızlar gökyüzünde parlıyor."),
            EnglishWords("Train", "treɪn", R.drawable.apple, "Tren", "The train arrived on time.", "Tren zamanında geldi."),
            EnglishWords("Violin", "vaɪəˈlɪn", R.drawable.apple, "Keman", "She plays the violin.", "Keman çalıyor."),
            EnglishWords("Watch", "wɑʧ", R.drawable.apple, "Saat", "I check the time on my watch.", "Saatime bakarak zamanı kontrol ederim."),
            EnglishWords("Yogurt", "ˈjoʊɡərt", R.drawable.apple, "Yoğurt", "I eat yogurt with fruit.", "Yoğurdu meyve ile yerim."),
            EnglishWords("Alarm", "əˈlɑrm", R.drawable.apple, "Alarm", "The alarm rang early in the morning.", "Alarm sabah erken çaldı."),
            EnglishWords("Beach", "biʧ", R.drawable.apple, "Plaj", "We walked along the beach.", "Plaj boyunca yürüdük."),
            EnglishWords("Camera", "ˈkæmərə", R.drawable.apple, "Kamera", "He took pictures with his camera.", "Kamerasıyla fotoğraf çekti."),
            EnglishWords("Drum", "drʌm", R.drawable.apple, "Davul", "She plays the drum in a band.", "Bir grupta davul çalıyor."),
            EnglishWords("Fork", "fɔrk", R.drawable.apple, "Çatal", "He used a fork to eat his salad.", "Salatasını yemek için çatal kullandı."),
            EnglishWords("Helmet", "ˈhɛlmɪt", R.drawable.apple, "Kask", "Always wear a helmet while riding a bike.", "Bisiklet sürerken her zaman kask tak."),
            EnglishWords("Island", "ˈaɪlənd", R.drawable.apple, "Ada", "They vacationed on a tropical island.", "Tropikal bir adada tatil yaptılar."),
            EnglishWords("Ladder", "ˈlædər", R.drawable.apple, "Merdiven", "He climbed the ladder to fix the roof.", "Çatıyı tamir etmek için merdivene tırmandı."),
            EnglishWords("Notebook", "ˈnoʊtbʊk", R.drawable.apple, "Defter", "I wrote the notes in my notebook.", "Notları defterime yazdım."),
            EnglishWords("Oven", "ˈʌvən", R.drawable.apple, "Fırın", "The cake is baking in the oven.", "Kek fırında pişiyor."),
            EnglishWords("Pillow", "ˈpɪloʊ", R.drawable.apple, "Yastık", "He rests his head on the pillow.", "Başını yastığa koyuyor."),
            EnglishWords("Rope", "roʊp", R.drawable.apple, "İp", "He tied the boat with a rope.", "Tekneyi bir ip ile bağladı."),
            EnglishWords("Scissors", "ˈsɪzərz", R.drawable.apple, "Makas", "She cut the paper with scissors.", "Kağıdı makasla kesti."),
            EnglishWords("Towel", "ˈtaʊəl", R.drawable.apple, "Havlu", "He dried his hands with a towel.", "Ellerini havluyla kuruttu.")
        )
        _words.value = wordList.shuffled()
    }

    fun refreshWords() {
        _words.value = _words.value?.shuffled()
    }
}
