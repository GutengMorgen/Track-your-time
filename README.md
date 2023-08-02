# Track your time
Una simple app de escritorio que aparecera cada cierto tiempo para que tu pongas una breve descripcion de la mierda que hiciste en esa hora, tambien incluira tags como studing, working, relax, offline, etc...

## About the Descriptions
Las descripciones se guardaran en dos archivos .csv:
- data: En este archivo solo se guardaran las 5 ultimas descripciones por 2 motivos:
  1. para acceder a esas ultimas descripciones y
  2. para que sea mas rapido leer los datos.
- history: En este archivo se guardaran todas las descripciones que se utilizaran para hacer los graficos y estadisticas.

## About the Tags
Los tags son tags para clasificar las descripciones, no hay nada mas que agregar

## About Shortcuts
luego de usar la app por una semana, he descubirto que es jodidamente/estresantemente/irritantemente/aburridamente/mierdosamente tener que poner manualmente los tags, se podria arreglar facilmente poniendo un shortcut para seleccionar los tags(ctrl + 1 para seleccionar el primer tag), 

pero en realidad acabo de darme cuenta que es mejor que luego de guardar la descripcion(presionando el boton o con ctrl + s) aparezca una ventana con los tags y luego presionar los `teclados numericos` para guardar y cerrar el popup o seleccionar el tag con las `flechas up and down` y con las `flecha right` guardar y cerrar el popup

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
