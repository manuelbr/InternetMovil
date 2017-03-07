##Apuntes de Internet Móvil

###Tema 2
* DCF: Sondea qué estaciones quieren transmitir.
* PCF: Compiten por transmitir.
* El ancho de banda está relacionado con la velocidad de transmisión, pero son dos cosas distintas.
* A mayor ancho de banda, mayor velocidad de transmisión. 
* Los protocolos que usan en zigbee son diferentes a los de Bluetooth.
* Zigbee es de menor alcance que Blutooth, diseñado para conexiones ligeras y uso habitual en domótica.
* NFC se usa en móviles para identificar tarjetas bancarias.
* No hacer ni caso a las diapositivas 27,28 y 29 del tema 2.
* UMTS es el sistema que usan los teléfonos móviles para tener conexión de red.
* 4G usa LTE.
* WiMax es el wifi de larga distancia.
* Los sistemas satélites son capaces de evitar los problemas geográficos de la tierra, pero son de baja velocidad.

###Tema 3
* La nomacidad no es la movilidad. Nomacidad es la posibilidad de trasladar un dispositivo y que 
en el origen y en el destino haya conexión. Movilidad es la provisión del servicio de conexión a 
lo largo del desplazamiento.
* DHCP es el protocolo que configura la conexión de dispositivos (router, dns, etc) y le da una ip. Se hace
automáticamente.
* Cuando se detectan cambios en la dirección MAC en la misma máquina, se hace la difusión de su localización
para que sea pública y para informar a todo el mundo que el usuario en su origen no es el mismo que en su destino.
* La transmisión se hace en las capas bajas de los protocolos de comunicación cuando las distancias son muy cortas.
En capas altas, hay un mayor rango de desplazamiento. El cambiar de operador (Movistar a Vodafone), cambiamos a nivel
de capas altas.
* Cuanto más bajo estemos en el protocolo más fácil es detectar los cambios en las direcciones MAC, porque este tipo de 
información va en la capa de enlace, a muy bajo nivel.
* Las MAC en sí no cambian, sino el nombre que les asigna dhcp a esas MAC. Si cambian, se detecta desplazamiento.
* Entre diferentes puntos de acceso, las tablas de identificación deben estar sincronizadas para proveer movilidad.
* El CAPWAP se usa en desplazamientos de distancia moderada, no muy largas.
* Si intervienen diferentes WLC de CAPWAP en lugares físicos, porque se alternen en su uso desde dispositivos que cambian
de posición.
* Una dirección ip no identifica dispositivos en internet, sino lugares físicos, ya que cambian al movernos de un sitio a otro con el mismo dispositivo.
* La transparencia en el IETF del protocolo IP se basa en la idea de que aunque una ip es variable en función de la localización, todo dispositivo tiene que tener una ip fija inalterable.
