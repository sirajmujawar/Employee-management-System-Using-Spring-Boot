import { Routes, Route } from "react-router-dom";
import AddEmployee from "./Component/AddEmployee";
import UpdateEmp from "./Component/UpdateEmp";
import DeleteEmp from "./Component/DeleteEmp";
import RecordEmp from "./Component/RecordEmp";
import Navbar from "./Component/Navbar";

const App = () => {
  return (
   <>
 <Navbar/>
     <Routes> 
      <Route path="/" element={<AddEmployee />} />
      <Route path="/update" element={<UpdateEmp />} />
      <Route path="/delete" element={<DeleteEmp />} />
      <Route path="/records" element={<RecordEmp />} />
    </Routes>
   </>
  );
};

export default App;