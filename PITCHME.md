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

<!-- * Anko [https://github.com/Kotlin/anko](https://github.com/Kotlin/anko) -->

#HSLIDE

**ContextExt**

```
fun Context.colorRes(colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.colorStateListRes(colorResId: Int): ColorStateList {
    return ContextCompat.getColorStateList(this, colorResId)
}
```

#HSLIDE

**ColorExt**

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

**ActivityExt**

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

**FragmentExt**

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

Si usamos Picasso, normalmente lo hariamos de esta forma.

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

**ImageViewExt**

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

fun Context.share(text: String, subject: String = ""): Boolean {...}
fun Context.email(email: String, subject: String = "", text: String = ""): Boolean {...}
```

#HSLIDE

**Intents**

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

fun Context.sendSMS(number: String, text: String = ""): Boolean {...}
```

#HSLIDE

**Dialogs**

```
fun Context.alert(title: String, message: String, 
    init: (AlertDialog.Builder.() -> Unit)? = null) 
        = AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(R.string.ok, { dialog, w -> dialog.dismiss() })
            init?.let { init() }
}
```

```
alert(title, message).show()
alert(title, message){...}.show()
```

#HSLIDE

**Referencias**

* Extension Functions [https://antonioleiva.com/extension-functions-kotlin/](https://antonioleiva.com/extension-functions-kotlin/)
* Anko [https://github.com/Kotlin/anko](https://github.com/Kotlin/anko)