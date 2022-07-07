;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	smi.ma. dns.smi.ma. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
	IN	A	192.168.1.1
;
@	IN	NS	dns.smi.ma.
dns	IN	A	192.168.1.1
serveur1	IN	A	192.168.1.1
pc1	IN	A	192.168.1.2
