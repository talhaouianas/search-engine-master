;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	fso.ump.ma. dns.fso.ump.ma. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
	IN	A	17.6.19.1
;
@	IN	NS	dns.fso.ump.ma.
dns	IN	A	17.6.19.2
www	IN	A	17.6.19.1
ftp	IN	A	17.6.19.3
