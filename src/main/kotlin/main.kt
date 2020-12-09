import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.URL


fun main(args: Array<String>) {
    val content = readContent(getURL("gra planszowa"))

    print(content)
}

fun readContent(baseURL: String): String {
    val url = URL(baseURL)
    val connection = url.openConnection()
    val bufferedReader = BufferedReader(InputStreamReader(connection.getInputStream()))
    var inputLine = bufferedReader.readLine()
    val stringBuilder = StringBuilder()

    while (inputLine != null){
        stringBuilder.append(inputLine)
        inputLine = bufferedReader.readLine()
    }
    return stringBuilder.toString()
}

fun getURL(queryParam: String): String {
    return "https://allegro.pl/listing?string=" + queryParam.replace(' ', '+')
}
