import {useEffect, useState} from "react";

export default function useWidth() {
    const [width, setWidth] = useState(0)

    function handleResize() {
        setWidth(window.innerWidth)
    }

    useEffect(() => {
        window.addEventListener("resize", handleResize)
        handleResize()
        return () => {
            window.removeEventListener("resize", handleResize)
        }
    }, [setWidth])

    return width
}