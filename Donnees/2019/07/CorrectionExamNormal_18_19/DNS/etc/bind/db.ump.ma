;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	ump.ma. dns.ump.ma. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
	IN	A	196.17.6.1
;
@	IN	NS	dns.ump.ma.
www	IN	A	196.17.6.1
nfs	IN	A	196.17.6.2
dhcp    IN  A       196.17.6.1
