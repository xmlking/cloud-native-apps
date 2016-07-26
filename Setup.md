Setup
=====
**cloud-native-apps** Project setup

### Cloning this Project with Submodules
Here we’ll clone this project with a submodule in it. When you clone such a project, 
by default you get the directories that contain submodules, but none of the files within them yet.
You can specifiy `--recursive` to automatically initialize submodule

```bash
mkdir /Developer/Work/boot
cd /Developer/Work/boot
# Cloning this Project with Submodules
git clone --recursive https://github.com/xmlking/cloud-native-apps.git
# Cloning cloud-config Project
git clone https://github.com/xmlking/cloud-config
```

### IDE
Import each project into IDE (IntelliJ preferred)