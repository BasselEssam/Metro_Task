import kotlin.math.abs

fun main() {
    val line1Stations = listOf(
        "helwan","ain helwan","helwan university","wadi hof","hadayek helwan","el-maasara",
        "tora el-asmant","kozzika","tora el-balad","thakanat el-maadi","maadi","hadayek el-maadi",
        "dar el-salam","el-zahraa","mar girgis","el-malek el-saleh","sayeda zeinab","saad zaghloul",
        "el-sadat","gamal abdel-nasser","orabi","el-shohadaa","ghamra","el-demerdash","manshiet el-sadr",
        "kobri el-kobba","hammamat el-kobba","saray el-kobba","hadayek el-zeitoun","helmeyet el-zeitoun",
        "el-matareyya","ain shams","ezbet el-nakhl","el-marg","new el-marg"
    )
    val line2Stations = listOf(
        "el-monib","sakiat mekki","om el-masryeen","giza","faisal","cairo university","el-bohooth","dokki",
        "opera","el-sadat","mohamed naguib","attaba","el-shohadaa","massara","rod el-farag","st. teresa",
        "el-khalafawy","el-mezallat","faculty of agriculture","shubra el-kheima"
    )
    val line3Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","sudan","imbaba","el-bohy","el-qawmia","ring road","rod el-farag corr"
    )
    val line4Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","el-tawfikia","wadi el nile","gamet el dowel","bulaq el-dakrour","cairo university"
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
            //println("different metro lines")
            val currentLine :List<String>
            val transiStations: List<String>
            if (line1Stations.contains(startStation)) {
                currentLine=line1Stations
                transiStations= mutableListOf("el-sadat","gamal abdel-nasser","el-shohadaa")
                for (i in 0..2){
                    println("---------Another way---------")
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine)
                }
            }
            else if (line2Stations.contains(startStation)) {
                currentLine=line2Stations
                transiStations=mutableListOf("cairo university","el-sadat","attaba","el-shohadaa")
                for (i in 0..3){
                    println("---------Another way---------")
                        //          helwan          el-monib    cairo universty   line 2
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine)
                }
            }
            else if (line3Stations.contains(startStation)) {
                currentLine=line3Stations
                transiStations=mutableListOf("attaba","gamal abdel-nasser")
                for (i in 0..1){
                    println("---------Another way---------")
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine)
                }
            }
            else {
                currentLine=line4Stations
                transiStations=mutableListOf("attaba","gamal abdel-nasser","cairo university")
                for (i in 0..2){
                    println("---------Another way---------")
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine)
                }
            }
            return
        }
    }
    val startIndex=stations.indexOf(startStation)
    val endIndex=stations.indexOf(arrivalStation)
    val numberOfStations=abs(endIndex - startIndex)
    println("number of stations= $numberOfStations")
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
//                  helwan                    el-monib             cairo university              line2
fun searchStations(finalArrivalStation:String,startStation:String,arrivalStation:String,currentLine:List<String>){
    val startIndex=currentLine.indexOf(startStation)//   0
    val endIndex=currentLine.indexOf(arrivalStation)//   5
    val numberOfStations=abs(endIndex - startIndex)//  5
    println("number of stations= $numberOfStations")  // 5
    //println("estimated time= ${numberOfStations * 2} min")
    if (endIndex > startIndex) {
        println("direction= ${currentLine.last()}")  // shubra
        println("stations: ${currentLine.slice(startIndex..endIndex)}")
    }
    else{
        println("direction= ${currentLine.first()}")
        println("stations: ${currentLine.slice(endIndex..startIndex).reversed()}")
    }
//                                      line2        cairo uni     helwan
    val currentLineModified=changeLine(currentLine,arrivalStation,finalArrivalStation) // line4
    if(arrivalStation!=finalArrivalStation){
        val startStationModified=arrivalStation  //  cairo uni
        if (currentLineModified.contains(finalArrivalStation))
            searchStations(finalArrivalStation,startStationModified,finalArrivalStation,currentLineModified)
        else{
            if (currentLineModified.contains("helwan")) {
                val transiStations= mutableListOf("el-sadat","gamal abdel-nasser","el-shohadaa")
                for (i in 0..2){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                    //println("---------Another way---------")
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified)
                    }
                }
            }
            else if (currentLineModified.contains("giza")) {
                val transiStations=mutableListOf("cairo university","el-sadat","attaba","el-shohadaa")
                for (i in 0..3){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                    //println("---------Another way---------")
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified)
                    }
                }
            }
            else if (currentLineModified.contains("rod el-farag corr")) {
                val transiStations=mutableListOf("attaba","gamal abdel-nasser")
                for (i in 0..1){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                    //println("---------Another way---------")
                                     //
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified)
                    }
                }
            }
            else {
                val transiStations=mutableListOf("attaba","gamal abdel-nasser","cairo university")
                for (i in 0..2){
                    //   cairo uni              attaba
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                    //println("---------Another way---------")
                    //              helwan              cairo uni            attaba            line4
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified)
                    }
                }
            }
        }
    }
}

fun changeLine (currentLine: List<String>, transitionStation:String,finalArrivalStation: String):List<String>{
    val line1Stations = listOf(
        "helwan","ain helwan","helwan university","wadi hof","hadayek helwan","el-maasara",
        "tora el-asmant","kozzika","tora el-balad","thakanat el-maadi","maadi","hadayek el-maadi",
        "dar el-salam","el-zahraa","mar girgis","el-malek el-saleh","sayeda zeinab","saad zaghloul",
        "el-sadat","gamal abdel-nasser","orabi","el-shohadaa","ghamra","el-demerdash","manshiet el-sadr",
        "kobri el-kobba","hammamat el-kobba","saray el-kobba","hadayek el-zeitoun","helmeyet el-zeitoun",
        "el-matareyya","ain shams","ezbet el-nakhl","el-marg","new el-marg"
    )
    val line2Stations = listOf(
        "el-monib","sakiat mekki","om el-masryeen","giza","faisal","cairo university","el-bohooth","dokki",
        "opera","el-sadat","mohamed naguib","attaba","el-shohadaa","massara","rod el-farag","st. teresa",
        "el-khalafawy","el-mezallat","faculty of agriculture","shubra el-kheima"
    )
    val line3Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","sudan","imbaba","el-bohy","el-qawmia","ring road","rod el-farag corr"
    )
    val line4Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","el-tawfikia","wadi el nile","cairo university","bulaq el-dakrour","cairo university"
    )
    if (currentLine.contains("helwan") && (transitionStation=="el-sadat" || transitionStation=="el-shohadaa"))
        return line2Stations
    else if (currentLine.contains("helwan") && transitionStation=="gamal abdel-nasser")
            if(line3Stations.contains(finalArrivalStation))
                return line3Stations
            else return line4Stations
    else if(currentLine.contains("el-monib") && (transitionStation=="el-sadat" || transitionStation=="el-shohadaa"))
        return line1Stations
    else if(currentLine.contains("el-monib") && transitionStation=="cairo university" )
        return line4Stations
    else if(currentLine.contains("el-monib") && transitionStation=="attaba")
        if(line3Stations.contains(finalArrivalStation))
            return line3Stations
        else return line4Stations
    else if(currentLine.contains("rod el-farag corr") && transitionStation=="attaba" )
        return line2Stations
    else if(currentLine.contains("rod el-farag corr") && transitionStation=="gamal abdel-nasser" )
        return line1Stations
    else if(currentLine.contains("bulaq el-dakrour") && (transitionStation=="attaba" || transitionStation=="cairo university"))
        return line2Stations
    else                        // in line 4 and transition at nasser
        return line1Stations
}

fun sameLine(currentLine: List<String>, arrivalStation: String):Boolean{
    return currentLine.contains(arrivalStation)
}

fun getCurrentLine (startStation: String):List<String>{
    val line1Stations = listOf(
        "helwan","ain helwan","helwan university","wadi hof","hadayek helwan","el-maasara",
        "tora el-asmant","kozzika","tora el-balad","thakanat el-maadi","maadi","hadayek el-maadi",
        "dar el-salam","el-zahraa","mar girgis","el-malek el-saleh","sayeda zeinab","saad zaghloul",
        "el-sadat","gamal abdel-nasser","orabi","el-shohadaa","ghamra","el-demerdash","manshiet el-sadr",
        "kobri el-kobba","hammamat el-kobba","saray el-kobba","hadayek el-zeitoun","helmeyet el-zeitoun",
        "el-matareyya","ain shams","ezbet el-nakhl","el-marg","new el-marg"
    )
    val line2Stations = listOf(
        "el-monib","sakiat mekki","om el-masryeen","giza","faisal","cairo university","el-bohooth","dokki",
        "opera","el-sadat","mohamed naguib","attaba","el-shohadaa","massara","rod el-farag","st. teresa",
        "el-khalafawy","el-mezallat","faculty of agriculture","shubra el-kheima"
    )
    val line3Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","sudan","imbaba","el-bohy","el-qawmia","ring road","rod el-farag corr"
    )
    val line4Stations=listOf(
        "adly mansour","el-haykestep","omar ibn el-khattab","qobaa","hesham barakat","el-nozha",
        "nadi el-shams","alf maskan","heliopolis","haroun","al-ahram","koleyet el-banat","stadium","fair zone",
        "abbassia","abdou pasha","el-geish","bab el-shaaria","attaba","gamal abdel-nasser","maspero","safaa hegazy",
        "kit kat","el-tawfikia","wadi el nile","cairo university","bulaq el-dakrour","cairo university"
    )
    return if (line1Stations.contains(startStation))
        line1Stations
    else if (line2Stations.contains(startStation))
        line2Stations
    else if (line3Stations.contains(startStation))
        line3Stations
    else
        line4Stations
}
