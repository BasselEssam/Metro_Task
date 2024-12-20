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
    val line3Branches= listOf("sudan","imbaba","el-bohy","el-qawmia","ring road","rod el-farag corr","el-tawfikia","wadi el nile","gamet el dowel","bulaq el-dakrour","cairo university")

    val allSolutions = AllSolutions()
    allSolutions.tempSolutions

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
            // different metro lines
            val currentLine :List<String>
            val transiStations: List<String>
            if (line1Stations.contains(startStation)) {
                currentLine=line1Stations
                transiStations= mutableListOf("el-sadat","gamal abdel-nasser","el-shohadaa")
                for (i in 0..2){
                    //println("---------Another way---------")
                    allSolutions.tempSolutions.add(mutableListOf())
                    allSolutions.allSolutions.add(mutableListOf())
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine,allSolutions)
                }
            }
            else if (line2Stations.contains(startStation)) {
                currentLine=line2Stations
                transiStations=mutableListOf("cairo university","el-sadat","attaba","el-shohadaa")
                for (i in 0..3){
                    //println("---------Another way---------")
                    allSolutions.tempSolutions.add(mutableListOf())
                    allSolutions.allSolutions.add(mutableListOf())
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine,allSolutions)
                }
            }
            else if (line3Stations.contains(startStation)) {
                currentLine=line3Stations
                transiStations=mutableListOf("attaba","gamal abdel-nasser")
                for (i in 0..1){
                    //println("---------Another way---------")
                    allSolutions.tempSolutions.add(mutableListOf())
                    allSolutions.allSolutions.add(mutableListOf())
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine,allSolutions)
                }
            }
            else {
                currentLine=line4Stations
                transiStations=mutableListOf("attaba","gamal abdel-nasser","cairo university")
                for (i in 0..2){
                    //println("---------Another way---------")
                    allSolutions.tempSolutions.add(mutableListOf())
                    allSolutions.allSolutions.add(mutableListOf())
                    searchStations(arrivalStation,startStation,transiStations[i],currentLine,allSolutions)
                }
            }
            allSolutions.classifySolutions(startStation,arrivalStation)
            allSolutions.separateSolutions(startStation,arrivalStation)
            if(line3Branches.contains(startStation) && line3Branches.contains(arrivalStation)){  // handling the branches in line3
                val route= mutableListOf(startStation,"kit kat",arrivalStation)
                allSolutions.separatedSolutions.add(route)
            }
            allSolutions.solutionsDetails()
            allSolutions.shortestRouteDetails()
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
        in 1..9 -> println ("Ticket Price= 8 EGP ( 4 EGP for people at age of 60 or older and military / 5 EGP for Disability)")
        in 10..16 -> println ("Ticket Price= 10 EGP ( 5 EGP for people at age of 60 or older and military / 5 EGP for Disability)")
        in 17..23 -> println ("Ticket Price= 15 EGP ( 8 EGP for people at age of 60 or older and military / 5 EGP for Disability)")
        else -> println("Ticket Price= 20 EGP ( 10 EGP for people at age of 60 or older and military / 5 EGP for Disability)")
    }
}

fun searchStations(finalArrivalStation:String,startStation:String,arrivalStation:String,currentLine:List<String>,allSolutions: AllSolutions){
    val startIndex=currentLine.indexOf(startStation)
    val endIndex=currentLine.indexOf(arrivalStation)
    if (endIndex > startIndex) {
        allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].add(startStation)
        allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].add(arrivalStation)
        allSolutions.clean()
    }
    else{
        allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].add(startStation)
        allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].add(arrivalStation)
        allSolutions.clean()
    }
    val currentLineModified=changeLine(currentLine,arrivalStation,finalArrivalStation)
    if(arrivalStation!=finalArrivalStation){
        val startStationModified=arrivalStation
        if (currentLineModified.contains(finalArrivalStation))
            searchStations(finalArrivalStation,startStationModified,finalArrivalStation,currentLineModified,allSolutions)
        else{
            if (currentLineModified.contains("helwan")) {
                val transiStations= mutableListOf("el-sadat","gamal abdel-nasser","el-shohadaa")
                for (i in 0..2){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i]))
                            return
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified,allSolutions)
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i])){
                            allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].subList(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].indexOf(transiStations[i]), (allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].lastIndex)+1).clear()
                        }
                    }
                }
            }
            else if (currentLineModified.contains("giza")) {
                val transiStations=mutableListOf("cairo university","el-sadat","attaba","el-shohadaa")
                for (i in 0..3){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i]))
                            return
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified,allSolutions)
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i])){
                            allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].subList(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].indexOf(transiStations[i]), (allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].lastIndex)+1).clear()
                        }
                    }
                }
            }
            else if (currentLineModified.contains("rod el-farag corr")) {
                val transiStations=mutableListOf("attaba","gamal abdel-nasser")
                for (i in 0..1){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i]))
                            return
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified,allSolutions)
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i])){
                            allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].subList(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].indexOf(transiStations[i]), (allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].lastIndex)+1).clear()
                        }
                    }
                }
            }
            else {
                val transiStations=mutableListOf("attaba","gamal abdel-nasser","cairo university")
                for (i in 0..2){
                    if (startStationModified!=transiStations[i] && transiStations[i] != startStation){
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i]))
                            return
                    searchStations(finalArrivalStation,startStationModified,transiStations[i],currentLineModified,allSolutions)
                        if(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].contains(transiStations[i])){
                            allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].subList(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].indexOf(transiStations[i]), (allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex].lastIndex)+1).clear()
                        }
                    }
                }
            }
        }
    }
    else{
        allSolutions.allSolutions[allSolutions.allSolutions.lastIndex].addAll(allSolutions.tempSolutions[allSolutions.tempSolutions.lastIndex])
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
