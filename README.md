# GuessNumber

Proyecto Android en el que el usuario debe tratar de adivinar un número que es generado aleatoriamente con valor entre 0 y 100. Realizado en Android Studio y con el lenguaje Kotlin.

El proyecto implementa el modelo de MVVM. La vista se comunica con los datos a través de clases ViewModel. 

Soporte en español y en inglés.

### - Primera vista. Configuración de la partida

<br>
<p align="center">
<img src="https://github.com/CBocka/GuessNumber/assets/156449965/1cd61d19-9867-4bfb-bed8-5c2d7b69a2ee" height="450" width="220" >
</p>
<br>

El usuario debe obligatoriamente introducir un nombre y un número de intentos en los que cree que será capaz de adivinar el número. 

Al pulsar en el botón de empezar la partida, si el estado es correcto se crea una instancia única de la clase GuessNumberGame que representa la partida a jugar.

### - Segunda vista. Ventana de juego

<br>
<p align="center">
<img src="https://github.com/CBocka/GuessNumber/assets/156449965/91d4ec6c-5328-48cf-9101-53c9ceefebf5" height="450" width="220" >
</p>
<br>

El usuario debe ir introduciendo un número entero entre 0 y 100 para tratar de adivinar el número generado para la partida. Si se comete algún error no se descuenta el intento.

Una vez pulsado el botón se comprueba el número introducido por el usuario y se responde en consecuencia. Si el usuario acertó se produce una navegación a la vista final.  Si falló se generá un diálogo usando una clase personalizada para indicar si el número a adivinar es mayor o menor.

### - Tercera vista. Fin de la partida

<br>
<p align="center">
<img src="https://github.com/CBocka/GuessNumber/assets/156449965/da6e3488-9cfc-43d3-9cb3-af0ba2ba8717" height="450" width="220" >
</p>
<br>

El usuario ha terminado la partida y o bien ha ganado si ha acertado el número en los intentos establecidos o ha perdido si se ha quedado sin intentos.

El botón permite volver a la vista de configuración e iniciar una nueva partida.
