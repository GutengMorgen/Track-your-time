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

## Shortcuts
luego de usar la app por una semana, he descubirto que es jodidamente/estresantemente/irritantemente/aburridamente/mierdosamente tener que poner manualmente los tags, se podria arreglar facilmente poniendo un shortcut para seleccionar los tags(ctrl + 1 para seleccionar el primer tag), 

pero en realidad acabo de darme cuenta que es mejor que luego de guardar la descripcion(presionando el boton o con ctrl + s) aparezca una ventana con los tags y luego presionar los `teclados numericos` para guardar y cerrar el popup o seleccionar el tag con las `flechas up and down` y con las `flecha right` guardar y cerrar el popup

-agregar una nueva funcion para que aparezca el popup con un shortcut

##Bugs/new Design
- [x] como se muestra tiempo de cada descripcion pasada
- [x] mostrar la hora actual al lado de la descripcion
- [] cambiar la forma de guardar el texto de la descripcion ya que no se admite dobles comillas
- [] poner el mayusculas todos los tipos(date, time, tag, description) de data.csv
	- o talvez deberia eliminarlos y solo obtener los contenidos de cada tipo por el indice del array
- [] poner todas las lineas del history.csv al `txtHistorial` y talvez un boton de refresh
- [] actualizar el texto del `lblPopupStatus` segun si el popup inicio o no
- [] actualizar el texto del `lblTimeStatus` segun el tiempo que falta para que aparezca el popup
- [] dar funcionalidad al boton `Stop`
- [] actualizar los items del combotag cuando se presione el boton `btnSetTags`
- [] actualizar el texto del `lblResult` cuando se presione el boton `btnSetTags` o `btnSetTemplate`