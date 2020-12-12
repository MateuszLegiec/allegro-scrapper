import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.IndexOutOfBoundsException
import java.lang.StringBuilder
import java.net.URL
import java.util.regex.Pattern


fun main() {
    val content = readContent(getURL("gra planszowa"))
    File("content.txt").writeText(content)
    val articles = getArticles(content)
    print(articles)
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

fun getArticles(html: String): Set<String> {
    val matcher = Pattern.compile("<a href.*\\/><\\/a>").matcher(html)
    val set = mutableSetOf<String>()
    try {
        while (matcher.find()) {
            set.add(matcher.group())
        }
    } catch (e: IndexOutOfBoundsException){
        return set
    }

    return set
}
