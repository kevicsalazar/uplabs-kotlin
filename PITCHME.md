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

###¿Qué librerias se están usando?

* Retrofit by Square [http://square.github.io/retrofit/](http://square.github.io/retrofit/)
* Picasso by Square [http://square.github.io/picasso/](http://square.github.io/picasso/)
* Dagger2 by Google [https://google.github.io/dagger/](https://google.github.io/dagger/)
* Gson by Google [https://github.com/google/gson](https://github.com/google/gson)

#HSLIDE

###Extension functions

Podemos extender la funcionalidad de las clases.

```
fun View.visible() {
    visibility = View.VISIBLE
}
```

* Anko [https://github.com/Kotlin/anko](https://github.com/Kotlin/anko)

#HSLIDE

**Picasso**

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

**Picasso**

Pero podemos crear una extensión de ImageView, esta sería loadUrl().

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