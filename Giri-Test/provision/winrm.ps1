winrm set winrm/config/service/auth '@{Basic="true"}'
winrm set winrm/config/service/auth '@{CredSSP="true"}'
winrm set winrm/config/client/auth '@{CredSSP="true"}'
winrm set winrm/config/service '@{AllowUnencrypted="true"}'
netsh advfirewall firewall add rule name="WinRM 5985" protocol=TCP dir=in localport=5985 action=allow
netsh advfirewall firewall add rule name="JBoss 9080" protocol=TCP dir=in localport=9080 action=allow
netsh advfirewall firewall add rule name="ICMP Allow incoming V4 echo request" protocol=icmpv4:8,any dir=in action=allow
Add-MpPreference -ExclusionPath "D:\"
mkdir d:\tools
mkdir -p d:\psping\log
#Invoke-WebRequest -Uri "http://cloud-ivy.temenosgroup.com/build/tools/psping.exe" -OutFile "d:\tools\psping.exe"
#echo "D:\tools\psping.exe -t 10.23.13.26:8081 /accepteula >D:\psping\log\psping-10.23.13.26-8081.log" > "D:\tools\psping-10.23.13.26.ps1"
#Start-Process "powershell.exe" -ArgumentList "-WindowStyle", "Hidden", "-File", "D:\tools\psping-10.23.13.26.ps1" -WindowStyle Hidden
#echo "D:\tools\psping.exe -t 10.23.7.106:8080 /accepteula >D:\psping\log\psping-10.23.7.106-8080.log" > "D:\tools\psping-10.23.7.106.ps1"
#Start-Process "powershell.exe" -ArgumentList "-WindowStyle", "Hidden", "-File", "D:\tools\psping-10.23.7.106.ps1" -WindowStyle Hidden
#echo "D:\tools\psping.exe -t 10.23.7.48:8080 /accepteula >D:\psping\log\psping-10.23.7.48-8080.log" > "D:\tools\psping-10.23.7.48.ps1"
#Start-Process "powershell.exe" -ArgumentList "-WindowStyle", "Hidden", "-File", "D:\tools\psping-10.23.7.48.ps1" -WindowStyle Hidden
