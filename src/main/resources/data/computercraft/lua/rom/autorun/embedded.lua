local function printMessageToMonitor()
    local monitors = {peripheral.find("monitor")}
    for _, monitor in pairs(monitors) do
        local oldColor1 = monitor.getBackgroundColor()
        local oldColor2 = monitor.getTextColor()
        monitor.setTextScale(1)
        monitor.setBackgroundColor(colors.blue)
        monitor.setTextColor(colors.white)
        monitor.setCursorBlink(false)
        monitor.clear()
        monitor.setCursorPos(2,2)
        term.redirect(monitor)
        print(":(")
        print("")
        print(" No startup found")
        print(" Insert a startup disk")
        monitor.setTextColor(oldColor2)
        monitor.setBackgroundColor(oldColor1)
    end
    term.redirect(term.native())
    sleep(10)
end

local function isEmbedded()
    local termX, termY = term.getSize()
    if termX == 10 and termY == 10 then
        return true
    end
end

if isEmbedded() then
    _G.embedded = {}
    function _G.embedded.format()
        local root = fs.list("/")
        for _,v in pairs(root) do
            pcall(fs.delete,"/"+v)
        end
    end
    if fs.exists("/startup.lua") then
        return
    end
    local found = fs.find("dis*/startup.lua")
    local found1 = fs.find("dis*/startup")
    if #found == 0 and #found1 == 0 then
        printMessageToMonitor()
    else
        return
    end
end