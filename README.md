# Installator for Linux (dpkg) and Windows 10/11 soon.

## To run on Linux before installator will be available, you need to:
	1. Install JRE 26
	2. Place files from <dir> --> <absolute/path>:
		- libs 					-->	 /usr/share/java/atlas/
		- logs 					-->	 /var/log/atlas/
		- configs 				-->	 /etc/atlas/
		- assets 				-->	 /usr/share/atlas/
	3. Place <file> --> <absolute/path>:
		- atlas.sh 				-->	 /bin/
		- atlas.desktop			--> /home/[USERNAME]/.local/share/applications/
	4. Files to ignore:
		- RunAtlas.vbs
		- RunAtlasServer.vbs
		- Atlas.bat
		- AtlasServer.bat
	5. All other files place at absolute path: /opt/atlas/

## To run on Windows 10/11 before installator will be available, you need to:
	1. Install JRE 26
	3. Create <dir> --> <absolute\path>:
		- config 				-->	[WINDOWS DRIVE LETTER]:\User\[USERNAME]\.atlas\
		- assets 				-->	[WINDOWS DRIVE LETTER]:\User\[USERNAME]\.atlas\
		- atlas 				-->	[WINDOWS DRIVE LETTER]:\User\[USERNAME]\Desktop\
	4. Create <shortcut> <-- <file> at [WINDOWS DRIVE LETTER]:\ProgramData\Microsoft\Windows\Start Menu\Programs\atlas\
		- Atlas 				<-- Atlas.vbs
		- Atlas Debug			<-- Atlas.bat
		- AtlasServer			<-- AtlasServer.vbs
		- AtlasServer Debug		<-- AtlasServer.bat
	5. File to ignore - atlas.sh
	6. Place all files at absolute path: [WINDOWS DRIVE LETTER]:\Program Files\atlas\
