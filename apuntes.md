## Apuntes de Internet Móvil

### Tema 2
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

### Tema 3
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
* ICMP e IGMP son protocolos que están montados sobre ip. IGMP se usa para distribuir datos a grupos de direcciones.
* En el encapsulamiento de los datos que se envían por ip, si los datos pertenecen a una capa N,
se le van añadiendo las cabeceras de las capas inferiores, hasta estar recubierto por todas.
* El proceso de conexión con ip movil es: descubrimiento de los puntos de acceso disponibles, registro en la red remota, visitante y Tunneling.
* El care-of-address es la dirección que hemos obtenido en la fase de descubrimiento.
* El PAP es un protocolo de autenticación, según el cuál nos identificamos con contraseñas que son conocidas por las dos partes que participan en la acción.
* El tunneling es la transferencia de los datos una vez que se ha hecho el descubrimiento y conexión con el agente remoto. Lo que se hace es encapsular y abstraer los datos de direcciones ip locales y externas, dentro de otra capa ip. Los datos de esas direcciones son básicamente: dirección origen y dirección destino. El tunneling se basa en abstraer estas direcciones para no tener que ver a alto nivel las conexiones con nodos externos al emisor y al receptor, simulando una conexión punto a punto. El tunneling inverso (Ingress Flirtering) consiste en que hay que hacer un filtrado de ips entrantes y salientes para evitar que haya alguien en medio que intercepte los mensajes de los que eres partícipe (si fuera así las direcciones de origen de los paquetes que salen serían diferentes a la ip verdadera del emisor).
* La recogida de la información que se transfiere por Tunneling se
* ARP es un protocolo de resolución de direcciones que asigna una dirección MAC física a una ip. Hace la correlación entre estos dos términos.

#### IPv6
* El servicio de diferenciado, la clase de transferencia de datos del protocolo ip6 se usa para diferenciar entre paquetes prioritarios y normales en una transferencia.
* La fragmentación en ip6 hace referencia a paquetes divididos que deben ser reorganizados cuando se tienen todos, haciendo caso a la etiqueta offset de la cabecera, que dice por donde se conecta un paquete con el conjunto al que pertenece como fragmento de él.
* La cabecera de Autenticación se basa en que el receptor decide si la información que recibe es correcta o no. Para ello se usa el CRC, que es un sistema de pares de bits según el cuál se comprueba que lo que se ha transmitido entre un punto y otro no ha sido alterado. Es como un par de contraseñas entre el emisor y el receptor.

### Tema 5

* Hay diferentes tipos de ataques: la intercepción (sólo se escucha el tráfico entre dos puntos), la modificación (se escucha y se modifica la información en el flujo entre dos puntos), la interrupción (un nodo interrumpe el flujo entre un emisor y un receptor) y la suplantación (un nodo se hace pasar por otro en la comunicación con un tercero). Ellos pueden clasificarse como activo y pasivos.
* Las motivaciones de los atacantes son: (MEECES) Por dinero, por entretenimiento, por entrada dentro de ciertos grupos sociales, por una ideología o causa, por ego y por status (por alcanzar cierto nivel dentro de la sociedad).
* 
