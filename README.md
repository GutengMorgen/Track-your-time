# Track your time/Time Dial
Una simple app de escritorio que aparecerá cada cierto tiempo para que puedas poner una breve descripción de lo que hiciste en un tiempo preestablecido. También incluirá etiquetas como studying, working, relax, offline, etc.

## Nota importante
Esta aplicación aún está en desarrollo ya que:
- Aún falta implementar los gráficos, que son **muy importantes**.
- Aún faltan muchas cosas por implementar que creo que serían útiles.
- Falta limpiar/refactorizar el código con buenas prácticas.
- Falta cambiar la forma en la que se guardan las descripciones, ya que guardarlas en un archivo .csv puede no ser lo mejor a largo plazo. *Pienso cambiarlo usando SQLite.*

Pero aun así, la cosa más importante es que el Timer funcione y que guardar las descripciones funcione, así que la app se puede usar.

## Usar la app
Para usar la aplicación, sigue estos pasos:
1. Clona este repositorio en tu PC.
2. Abre el archivo **Time Dial.jar**.
3. Pulsa el botón **Now** para iniciar instantáneamente el **Popup**, o pulsa el botón Start para iniciar el **Popup** cuando el timer llegue a cero.
4. Cuando el timer llegue a cero, aparecerá una ventana, que es el **Popup**, ahí puedes poner una descripción y seleccionar una etiqueta.
5. Luego, puedes cerrarlo haciendo clic en el botón **Save** o con el comando `ctrl + s`.
6. Espera a que el timer llegue a cero nuevamente para que aparezca el **Popup**.

## About the Descriptions
Las descripciones se guardarán en dos archivos .csv:
- data: En este archivo solo se guardarán las últimas 5 descripciones por 2 motivos:
  1. Para acceder a esas últimas descripciones y
  2. Para que sea más rápido leer los datos.
- history: En este archivo se guardarán todas las descripciones que se utilizarán para hacer los gráficos y estadísticas.

## About the Tags
Los tags son tags para clasificar las descripciones, no hay nada mas que agregar

## About Shortcuts
- para navergar por los tags:
  - `ctrl + left` para ir al siguiente tag
  - `ctrl + right` para ir al tag previo
- para navegar por las descripciones de data.csv:
  - `ctrl + up` para ir a la siguiente descripcion
  - `ctrl + down` para ir a la descripcion previa

# Contribuciones
¡Sí, por favor! Solo mira las cosas que faltan/quiero implementar. Además, estoy considerando crear un nuevo repositorio para hacer este proyecto más formal y organizar todo de una mejor manera utilizando Maven o Gradle.

## new Design
- [ ] agregar una nueva funcion para que aparezca el popup con un shortcut
- [ ] agregar una opcion para poner mensajes/alertas programado cuando aparezca el popup en x tiempo
- [ ] agregar una opcion para poner (un texto o un tag) lo que se supone que vaz a hacer cuando aparezca el siguiente popup
- [ ] agregar un pomotoro timer en el mainframe
- [ ] poner una alerta(o algo parecido) cuando se intenta navegar por el data y existe texto en el `txtDescription`
- [ ] agregar una opcion para poder cambiar la posicion de los tags

## Bugs/new Features
- [ ] poner el codigo en un projecto mavel para manejar las futuras dependecias
- [ ] usar SQLite para guardar los textos
	- cambiar la forma de guardar el texto de la descripcion ya que no se admite dobles comillas
- [ ] crear un archivo para guardar las configuraciones
	- hacer los shortcuts editables
- [x] como se muestra tiempo de cada descripcion pasada
- [x] mostrar la hora actual al lado de la descripcion
- [x] poner en mayusculas todos los tipos(date, time, tag, description) de data.csv
	- o talvez deberia eliminarlos y solo obtener los contenidos de cada tipo por el indice del array
- [x] poner todas las lineas del history.csv al `txtHistorial` y talvez un boton de refresh
- [x] actualizar el texto del `lblPopupStatus` segun si el popup inicio o no
- [x] actualizar el texto del `lblTimeStatus` segun el tiempo que falta para que aparezca el popup
- [x] dar funcionalidad al boton `Stop`
- [x] actualizar los items del combotag cuando se presione el boton `btnSetTags`
- [x] actualizar el texto del `lblResult` cuando se presione el boton `btnSetTags` o `btnSetTemplate`
- [x] hacer que en el `txtTags` se puedan crear/eliminar tags 
- [ ] hacer que el mainframe aparezca cuando se windows inicie
- [x] cambiar el modo de cerrar del mainFrame por el de poner en icono de notificacion
- [x] refactorizar el timer o como se llama al popup 
	- hacer que el timer sea un countdown y que sea manejado por el mainframe
	- cuando se ejecute el `SaveClose()` method que mande una "señal" al mainframe para que este obtenga el item seleccionado del `comboTime` y que empieze el countdown timer
	- y cuando el countdown timer este en cero se ejecute el popup.
		- hacer que el countdown timer tenga un parametro(talvez default 0) que si este es 0 se ejecute inmediatamente el popup
- [x] en MainFrame/home crear dos botones: 1. para ejecutar el popup inmediatamente y 2. para ejecutar el popup segun el tiempo establecido en el `comboTime`
- [ ] poner los ActionListener de los botones en una nueva clase
- [ ] crear una alerta general para manejar las excepciones

- [-] refactorizar DataManager
- [x] refactorizar DateHandler
- [x] refactorizar MainFrame
- [x] refactorizar MyItems
- [x] refactorizar Popup
- [ ] refactorizar ShortcutManager
- [x] refactorizar Singleton
- [x] refactorizar TimerHandler


- [x] nueva gui para tags
- [x] un boton para crear tags
 	- contendra un textfield para poner el nombre del tag, luego lo guardara el el file y actualizara el comoTags
- [x] un boton para actualizar tags
 	- contendra el comboTags y un textField, se seleccionara el item del comboTags y luego se actualizara con el texto del textField
 	- incluir un textArea que cuando se seleccione un item se actualizara el textArea con el template del item
 	- cuando se presione el boton se actualizara el tag con el texto del textField y se actualizara el template con el texto del textArea(iterar las lineas para actualizarlo)
- [x] un boton para eliminar tags
	- contendra el comboTags y cuando se seleccione un item entonces se eliminara ese item del file Template.csv
