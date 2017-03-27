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