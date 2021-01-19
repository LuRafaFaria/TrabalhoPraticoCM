package ipca.followthroughapp.culinaria

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RecipeAPIBackend {

    companion object
    {

        suspend fun getLatestRecipes() : String = suspendCoroutine {
            var result = ""
            try
            {
                val urlc: HttpURLConnection = URL("https://api.spoonacular.com/recipes/complexSearch?apiKey=05500f65f09449679720dbbd0ede9d7a").openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Test")
                urlc.setRequestProperty("Connection", "close")
                urlc.setConnectTimeout(1500)
                urlc.connect()
                val stream  = urlc.inputStream
                val isReader = InputStreamReader(stream)
                val brin = BufferedReader(isReader)
                var str: String = ""



                var keepReading = true
                while (keepReading)
                {
                    var line = brin.readLine()
                    if (line == null)
                    {
                        keepReading = false
                    }
                    else
                    {
                        str += line

                        Log.d("gomes", line.toString())
                    }

                }

                brin.close()
                result = str
            }
            catch (e: Exception)
            {
                e.printStackTrace()
                result = "Sem Internet"
            }
            it.resume(result)

        }

        suspend fun getSearchedRecipes(name : String) : String = suspendCoroutine {
            var result = ""
            try
            {
                println("https://api.spoonacular.com/recipes/complexSearch?apiKey=1b985bb64ab34fedb4de82190e7ba438&query="+name)
                val urlc: HttpURLConnection = URL("https://api.spoonacular.com/recipes/complexSearch?apiKey=1b985bb64ab34fedb4de82190e7ba438&query="+name).openConnection() as HttpURLConnection
                urlc.setRequestProperty("User-Agent", "Test")
                urlc.setRequestProperty("Connection", "close")
                urlc.setConnectTimeout(1500)
                urlc.connect()
                val stream  = urlc.inputStream
                val isReader = InputStreamReader(stream)
                val brin = BufferedReader(isReader)
                var str: String = ""

                var keepReading = true
                while (keepReading)
                {
                    var line = brin.readLine()
                    if (line == null)
                    {
                        keepReading = false
                    }
                    else
                    {
                        str += line

                    }

                }

                brin.close()
                result = str

            }
            catch (e: Exception)
            {
                e.printStackTrace()
                result = "Sem Internet"
            }
            it.resume(result)


            Log.d("result", result.toString())

        }

        fun getBitmapFromURL(urlString : String, onBitmapResult : (Bitmap)->Unit)
        {

            object : AsyncTask<Unit, Unit, Bitmap>()
            {
                override fun doInBackground(vararg params: Unit?): Bitmap
                {
                    val input = URL(urlString).openStream()
                    var bMap = BitmapFactory.decodeStream(input)

                    return bMap
                }

                override fun onPostExecute(result: Bitmap?)
                {
                    super.onPostExecute(result)
                    result?.let {
                        onBitmapResult.invoke(result)
                    }
                }
            }.execute(null, null, null)


        }
    }
}