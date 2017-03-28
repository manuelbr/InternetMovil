##Internet Movil
auto enp0s3
iface enp0s3 inet static
address 30.30.30.1
netmask 255.255.255.0
broadcast 30.30.30.255
network 30.30.30.0

HOST INTERMEDIO
sudo route add -net 10.10.10.0 netmask 255.255.255.0 gw 20.20.20.21
sudo route add -net 30.30.30.0 netmask 255.255.255.0 gw 20.20.20.22

R1
sudo ifconfig enp0s3 10.10.10.22 netmask 255.255.255.0 broadcast 10.10.10.255
sudo ifconfig enp0s8 20.20.20.21 netmask 255.255.255.0 broadcast 20.20.20.255
sudo route add default gw 20.20.20.22
La eth0 tiene que tener la 20, la eth1 la 10
echo 1 > /proc/sys/net/ipv4/ip_forward

R2
sudo ifconfig enp0s3 30.30.30.22 netmask 255.255.255.0 broadcast 30.30.30.255
sudo ifconfig enp0s8 20.20.20.22 netmask 255.255.255.0 broadcast 20.20.20.255
sudo route add default gw 20.20.20.21
La eth0 tiene que tener la 30, la eth1 la 20
echo 1 > /proc/sys/net/ipv4/ip_forward

HAY QUE INSTALAR libgmp3-dev con una red nat y después hacer ./configure en la carpeta.
Después hacemos sudo make install

###########################################MN
TODAS LAS COSAS QUE PIDEN SE PUEDE DEJAR POR DEFECTO Y LUEGO SE CAMBIA EN EL FICHERO DE CONFIGURACIÓN
dynamics-mn-setup
vi /usr/local/etc/dynmnd.conf 
- ponemos 10.10.10.2 en MNHomeIPAddress
- ponemos 10.10.10.1 en HAIPAddress
- ponemos a TRUE el EnableFADecapsulation
- HomeNetPrefix lo ponemos a 10.10.10.0/24

###########################################HA
dynamics-ha-setup
vi /usr/local/etc/dynmnd.conf 
- En interfaces_begin, comprobar que tenemos las interfaces con los prefijos adecuados (10 o 20)
- Ponemos 1000 10.10.10.0 en la sección AUTHORIZEDLIST_BEGIN para que todos los dispositivos puedan acceder

###########################################FA
dynamics-fa-setup
vi /usr/local/etc/dynmnd.conf 
- ponemos HigestFaipaddress 30.30.30.1 y el UpperFaipaddress lo comentamos en caso de que esté puesta y en interfaces_begin comprobamos que está el nombre correcto de la interfaz.

##Hay que documentar y hacer capturas de pantalla de todo lo siguiente.

##Proceso de discovery (captando paquetes de aviso)
- Ponemos en marcha todo el proceso con dynfad --fg --debug  en la máquina FA.
- Ponemos en marcha todo el proceso con dynhad --fg --debug  en la máquina HA. 
- Si ejecutamos el wireshark en MN obtendremos los orígenes de varios paquetes que se envían de HA a FA. Se tiene que ver que el que envía es 10.10.10.1
- Si ejecutamos el wireshark en R2 obtendremos los orígenes de varios paquetes que se envían de FA a FA. Se tiene que ver que el que envía es 30.30.30.1
- Ejecutamos dynmnd --fg --debug en MN
- Hacemos traceroute 10.10.10.2 en el Host para comprobar que todo va bien

##Proceso de Tunneling
- EN el virtualbox en MN ponemos que la red del adapatador 1 en vez que sea la red base es la red visitada (Sin tener que apagar la máquina).
- Si en el host hago un traceroute 10.10.10.2 debería dar 4 pasos: 20.20.20.21 a 10.10.10.1 a 30.30.30.1 a 10.10.10.2
- Si en el MN hacemos traceroute 20.20.20.2 da 4 passos: 30.30.30.1 a 10.10.10.1 a 10.10.10.22 a 20.20.20.2 
- Si en el MN hacemos ping 20.20.20.2, hacemos una captura de tráfico en R1 con el wireshark.

##Proceso de Registro (captación de paquetes de registro)
- MN está en la red visitada y R2 con el wireshark abierto capturando. debemos obtener paquetes de 10.10.10.1 de REG REQUEST y 30.30.30.1 de REG REPLY.
- Hacemos ping de HOst a FA y si cambiamos MN  a red base, al hacer ping de HOST a MN es un único paso directo.
