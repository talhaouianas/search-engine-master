;
; BIND data file for local loopback interface
;
$TTL	604800
@	IN	SOA	sma.ma. dns.sma.ma. (
			      2		; Serial
			 604800		; Refresh
			  86400		; Retry
			2419200		; Expire
			 604800 )	; Negative Cache TTL
	IN	A	196.100.2.1
;
@	IN	NS	dns.sma.ma.
dns	IN	A	196.100.2.1
pc2	IN	A	196.100.2.2
