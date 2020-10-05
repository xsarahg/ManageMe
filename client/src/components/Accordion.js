import React, { useState, useRef, useEffect } from 'react'

const Accordion = props => {
    const [active, setActive] = useState(false);
    const contentRef = useRef(null);

    //Needed to allow phases to collapse
    useEffect(() => {
        contentRef.current.style.maxHeight = active ? `${contentRef.current.scrollHeight}px` : '0px'
    }, [contentRef, active]);

    const toggleActive = () => {
        setActive(!active);
    };

    return (
        <div className="accordion-section">
            <button className="accordion-title" onClick={toggleActive}>
                <p>{props.title}</p>
                <span className={active ? 'accordion-icon rotate': 'accordion-icon'}>
          >
        </span>
            </button>

            <div
                ref={contentRef}
                className="accordion-content"
            >
                {props.children}
            </div>
        </div>
    )
};

export default Accordion