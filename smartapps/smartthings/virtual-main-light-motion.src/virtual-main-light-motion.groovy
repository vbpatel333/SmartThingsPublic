definition(
    name: "Virtual Main Light Motion",
    namespace: "smartthings",
    author: "SmartThings",
    description: "Turn your lights on when motion is detected.",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/light_motion-outlet.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/light_motion-outlet@2x.png"
)

preferences {
    section("Real Switch...") { 
        input "realswitch", "capability.switch", 
	title: "Real Switch...", 
        required: true
    }
    section("Virtual Stand-in...") {
    	input "standin", "capability.switch",
        title: "Stand In Virtual Switch...",
        required: true
    }
}

def installed() {
    subscribe(standin, "switch.on", switchOnHandler)
    subscribe(standin, "switch.off", switchOffHandler)
}

def updated() {
    unsubscribe()
    subscribe(standin, "switch.on", switchOnHandler)
    subscribe(standin, "switch.off", switchOffHandler)
}

def switchOnHandler(evt) {
    state.wasOff = realswitch.currentValue("switch") == "off"
    if(state.wasOff)realswitch.on()
}

def switchOffHandler(evt) {
    if(state.wasOff)realswitch.off()
}