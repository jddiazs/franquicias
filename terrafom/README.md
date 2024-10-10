Para generar llave 
ssh-keygen -t rsa -b 4096 -m pem -f nequi_kp && openssl rsa -in nequi_kp -outform pem && chmod 400 nequi_kp.pem