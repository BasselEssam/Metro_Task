import kotlin.math.abs

fun main() {
    val line1Stations = listOf(
        "helwan","ain helwan","helwan university","wadi hof","hadayek helwan","el-maasara",
        "tora el-asmant","kozzika","tora el-balad","thakanat el-maadi","maadi","hadayek el-maadi",
        "dar el-salam","el-zahraa","mar girgis","el-malek el-saleh","sayeda zeinab","saad zaghloul",
        "el-sadat","gamal abdel nasser","orabi","el-shohadaa","ghamra","el-demerdash","manshiet el-sadr",
        "kobri el-kobba","hammamat el-kobba","saray el-kobba","hadayek el-zeitoun","helmeyet el-zeitoun",
        "el-matareyya","ain shams","ezbet el-nakhl","el-marg","new el-marg"
    )
    val line2Stations = listOf(
        "el-moneeb","sakiat mekki","om el-masryeen","giza","faisal","cairo university","el-bohooth","dokki",
        "opera","el-sadat","mohamed naguib","el-ataba","el-shohadaa","massara","rod el-farag","st. teresa",
        "el-khalafawy","el-mezallat","faculty of agriculture","shubra el-kheima"
    )
    val line3Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","sudan","imbaba","el-bohy","el-qawmia","ring road","rod el-farag corr."
    )
    val line4Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","el-tawfikia","wadi el nile","cairo university","bulaq el-dakrour","cairo university"
    )

//    entering start station
    println("enter start station")
    var startStation = readln().trim().lowercase()
    while (startStation !in (line1Stations + line2Stations + line3Stations + line4Stations)) {
        println("wrong station")
        println("enter start station again")
        startStation = readln().trim().lowercase()
    }

//    entering arrival station
    println("enter arrival station")
    var arrivalStation= readln().trim().lowercase()
    while (arrivalStation !in (line1Stations + line2Stations + line3Stations + line4Stations)) {
        println("wrong station")
        println("enter arrival station again")
        arrivalStation = readln().trim().lowercase()
    }
    if (arrivalStation == startStation) {
        println("the same station")
        return
    }

//    (number, time, direction, route) of stations
    val stations= when{
        line1Stations.contains(startStation) && line1Stations.contains(arrivalStation) -> line1Stations
        line2Stations.contains(startStation) && line2Stations.contains(arrivalStation) -> line2Stations
        line3Stations.contains(startStation) && line3Stations.contains(arrivalStation) -> line3Stations
        line4Stations.contains(startStation) && line4Stations.contains(arrivalStation) -> line4Stations
        else ->{
            println("different metro lines")
            return
        }
    }
    val startIndex=stations.indexOf(startStation)
    val endIndex=stations.indexOf(arrivalStation)
    val numberOfStations=abs(endIndex - startIndex)
    println("number of stations= stations")
    println("estimated time= ${numberOfStations * 2} min")
    if (endIndex > startIndex) {
        println("direction= ${stations.last()}")
        println("stations: ${stations.slice(startIndex..endIndex)}")
    }
    else{
        println("direction= ${stations.first()}")
        println("stations: ${stations.slice(endIndex..startIndex).reversed()}")
    }

//    ticket calculations
    when (numberOfStations) {
        in 1..9 -> println ("ticket= 8 EGP")
        in 10..16 -> println ("ticket= 10 EGP")
        in 17..23 -> println ("ticket= 15 EGP")
        else -> println("ticket= 20 EGP")
    }
    println("Ticket price for (age>60 years, Military, police) write 1 - (Disability) write 2")
    val answer= readln()
    if (answer=="1") {
        when (numberOfStations) {
            in 1..9 -> println("ticket= 4 EGP")
            in 10..16 -> println("ticket= 5 EGP")
            in 17..23 -> println("ticket= 8 EGP")
            else -> println("ticket= 10 EGP")
        }
    }
    else if (answer=="2") println("ticket= 5 EGP")
}