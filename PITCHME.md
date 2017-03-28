#HSLIDE

**Kevin Salazar Llashag**

[https://github.com/kevicsalazar](https://github.com/kevicsalazar)

#HSLIDE

<strong>Kotlin Extensions</strong>

#HSLIDE

[**UpLabs App**](https://www.uplabs.com)

<img src="./art/screenshot1.png" height="420" />
<img src="./art/screenshot2.png" height="420" />
<img src="./art/screenshot3.png" height="420" />

#HSLIDE

###Extension functions

Podemos extender la funcionalidad de las clases.

```
fun View.visible() {
    visibility = View.VISIBLE
}
```

#HSLIDE

**Context Extensions**

```
fun Context.colorRes(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.colorStateListRes(colorResId: Int): ColorStateList {
    return ContextCompat.getColorStateList(this, colorResId)
}
```

#HSLIDE

**Color Extensions**

```
typealias MaterialColor = Int

fun MaterialColor.palette() = ColorPalette(
        ...,
        shadeColor(0.0),        //500
        shadeColor(-0.125),     //600    
        shadeColor(-0.25),      //700
        ...
)

fun MaterialColor.shadeColor(percent: Double): Int {...}
```

```
setStatusBarColor(color.palette().C700)
toolbar.setBackgroundColor(color)
```

#HSLIDE

**Activity Extensions**

```
fun FragmentActivity.replaceContentFragment(containerViewId: Int, 
                                fragment: Fragment?): Fragment? {
    supportFragmentManager
            ?.beginTransaction()
            ?.replace(containerViewId, fragment)
            ?.commit()
    return fragment
}

fun AppCompatActivity.setStatusBarColor(color: Int) {
    if (lollipopOrNewer()) window.statusBarColor = color
}
```

#HSLIDE

**Fragment Extensions**

```
fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any>): T {
    arguments = bundleOf(*params)
    return this
}
```

```
replaceContentFragment(R.id.layoutContent, PageFragment().withArguments("type" to "ios"))
```

#HSLIDE

**ImageView**

Cargando una imagen.

```
Picasso.with(context).load(url).into(imageView)
```

```
Picasso.with(context)
    .load(url)
    .transform(CropCircleTransformation())
    .into(imageView)
```

#HSLIDE

**ImageView Extensions**

Creando extensiones de ImageView.

```
fun ImageView.load(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun ImageView.loadCircle(url: String) {
    Picasso.with(context)
        .load(url)
        .transform(CropCircleTransformation())
        .into(this)
}
```

```
imageView.load(url)
imageView.loadCircle(url)
```

#HSLIDE

**Intents**

Abrir una url en el navegador

```
fun Context.browse(url: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
        return true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }
}
```

```
browse("https://www.google.com.pe/")
```

#HSLIDE

**Intents**

Compartir un texto

```
fun Context.share(text: String, subject: String = ""): Boolean {
    try {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, null))
        return true
    } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        return false
    }
}
```

```
share("Hola a todos!!!")
```

#HSLIDE

**Intents**

Hacer una llamada

```
fun Context.makeCall(number: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
        return true
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
}
```

```
makeCall("987654321")
```

#HSLIDE

**Dialogs**

```
fun Context.alert(title: String, message: String, 
    init: (AlertDialog.Builder.() -> Unit)? = null) 
        = AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(R.string.ok, { 
                dialog, w -> dialog.dismiss() 
            })
            init?.let { init() }
}
```

```
alert(title, message).show()
alert(title, message){...}.show()
```

#HSLIDE

**Retrofit**

```
fun <T> Call<T>.enqueue(success: (response: T) -> Unit, 
                        failure: (t: String) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, 
                                response: Response<T>) {
            success(response.body())
        }
        override fun onFailure(call: Call<T>?, t: Throwable) {
            failure(t.message ?: "Unknown Error")
            t.printStackTrace()
        }
    })
}
```

```
ws.getPosts(type).enqueue({ /*Success*/ }, { /*Error*/ })
```

#HSLIDE

**Gson Extensions**

* JsonObject

```
val obj: JsonObject = jsonObject(
    "name" to "kotson",
    "creation" to Date().getTime(),
    "files" to 4
)
```

#HSLIDE

**Gson Extensions**

* JsonArray

```
val arr: JsonArray = jsonArray("one", "two", 42, 21.5)
```

#HSLIDE

**Preferences Extensions**

```
fun SharedPreferences.put(key: String, value: Any) {
    when (value) {
        ...
        is String     -> edit().putString(key, value).apply()
        is Date       -> edit().putLong(key, value.time).apply()
        is JsonObject -> edit().putString(key, value.toString()).apply()
        is JsonArray  -> edit().putString(key, value.toString()).apply()
        else          -> edit().putString(key, Gson().toJson(value)).apply()
    }
}
```

```
pref.put("name", "Kevin Salazar")
```

#HSLIDE

**Preferences Extensions**

```
fun SharedPreferences.string(key: String, 
    default: String? = null): String? = getString(key, default)
```

```
pref.string("name")
```

#HSLIDE

**Logs**

```
fun e(tag: String, message: String) {
    Log.e(tag, message)
}
fun Any.e(message: String) {
    Log.e(this.javaClass.name, message)
}
```

```
e("Error", "Algo está mal!!!")
e("Algo está mal!!!")
```

#HSLIDE

**Referencias**

* Extension Functions [https://antonioleiva.com/extension-functions-kotlin/](https://antonioleiva.com/extension-functions-kotlin/)
* Gson Extensions (Kotson) [https://github.com/SalomonBrys/Kotson](https://github.com/SalomonBrys/Kotson)
* SharedPreferences [https://github.com/kevicsalazar/SharedPreferences](https://github.com/kevicsalazar/SharedPreferences)
* More Extensions (Anko) [https://github.com/Kotlin/anko](https://github.com/Kotlin/anko)