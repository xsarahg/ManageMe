import React, {useState} from 'react';
import AuthService from "../services/auth.service";
import RoadmapService from "../services/roadmap.service";
import PleaseLogin from "../components/PleaseLogin";
import Accordion from "../components/Accordion";

function Roadmap(){
    const currentUser = AuthService.getCurrentUser();
    const userId = currentUser.id;

    //variables to be filled with data later
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [faseAmount, setFaseAmount] = useState("");
    const [currentPhase,setCurrentPhase] = useState(null);
    const [currentTask, setCurrentTask] = useState(null);
    const [currentTitle,setCurrentTitle] = useState("");
    const [currentDescription, setCurrentDescription] = useState("");

    if (currentUser) { //check if user is logged in
        RoadmapService.getRoadmap(userId).then( //search for the roadmap connected to this user
            (response) => {
                if (response.data) {
                    JSON.stringify(response.data);
                    const id = response.data.roadmapTemplateId; //get the roadmap id
                    RoadmapService.getRoadmapTemplate(id).then( //search for roadmap template based on id
                        (response)=>{
                            JSON.stringify(response.data); //contains data to build the roadmap
                            //fill variables based on response
                            setTitle(response.data.title);//page title
                            setDescription(response.data.description);//page description
                            setFaseAmount(response.data.num_of_fases);//currently unused, but could be used to dynamically generate the accordion menu

                            //if an item is selected, show content (title and description)
                            if (currentPhase != null){
                                setCurrentTitle(response.data.fases[currentPhase].subfases[currentTask].title);
                                setCurrentDescription(response.data.fases[currentPhase].subfases[currentTask].description);
                            }
                        }
                    )
                }
                return response.data;
            }
        );

        return (
            <div className="content">
                {/*start roadmap content*/}
                <div className="roadmap-intro">
                    <h1>{title}</h1>
                    <p>{description}</p>
                </div>
                <div className="roadmap-wrapper">

                    {/*accordion is the side menu*/}
                    <div className="accordion-wrapper">
                        <Accordion title="Phase 1">
                            <ul>
                                {/*each item has an onClick which sets the correct title and description for later use*/}
                                {/*the values seem random, but this is how they are stored in the array. we have no idea why and didn't have the time to fix it. It still works, it just looks weird*/}
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(1);setCurrentTask(1)}}>Biography</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(1);setCurrentTask(0)}}>Link to portfolio</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(1);setCurrentTask(2)}}>Upload pictures</button></li>
                            </ul>
                        </Accordion>
                        <Accordion title="Phase 2">
                            <ul>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(0);setCurrentTask(1)}}>Time management</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(0);setCurrentTask(3)}}>Writing</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(0);setCurrentTask(0)}}>Personal development</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(0);setCurrentTask(2)}}>Writing a CV</button></li>
                            </ul>
                        </Accordion>
                        <Accordion title="Phase 3">
                            <ul>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(2);setCurrentTask(1)}}>Upload demos</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(2);setCurrentTask(2)}}>Profile</button></li>
                                <li><button type="button" className="accordion-item" onClick={()=> {setCurrentPhase(2);setCurrentTask(0)}}>Your EPK</button></li>
                            </ul>
                        </Accordion>
                    </div>

                    {/*if there are a title and description (chosen an item from menu)*/}
                    {currentTitle && currentDescription &&
                        <div className="roadmap-content">
                            <h2>{currentTitle}</h2>
                            <p>{currentDescription}</p>
                        </div>
                    }
                    {/*if nothing is selected*/}
                    {!currentTitle &&
                        <div className="roadmap-content">
                            <h3>Use the menu on the left to get started!</h3>
                        </div>
                    }
                </div>

            </div>
        );
    }
    else{ //if user is not logged in
        return(
            <div className="content">
                <PleaseLogin/>
            </div>
        );
    }
}

export default Roadmap;