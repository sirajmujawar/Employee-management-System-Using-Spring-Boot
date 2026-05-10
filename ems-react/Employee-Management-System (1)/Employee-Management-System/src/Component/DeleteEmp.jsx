import { useState } from "react";
import "../css/App.css"

const DeleteEmp = () => {
  const [empId, setEmpId] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!empId) {
      setMessage("Please enter Employee ID");
      return;
    }

    console.log("Delete Employee ID:", empId);

    // 🔥 API Call (Spring Boot)
    
    fetch(`http://localhost:8080/employees/${empId}`, {
      method: "DELETE",
    })
      .then(() => setMessage("Employee deleted successfully"))
      .catch(() => setMessage("Error deleting employee"));

    setEmpId("");
    setMessage("Employee deleted successfully!");
  };

  return (
    <>
      <h1>Delete Employee</h1>

      <div className="emp">
        <form onSubmit={handleSubmit}>
          
          <label>EmpId</label>
          <input
            type="number"
            placeholder="Enter Employee ID"
            value={empId}
            onChange={(e) => setEmpId(e.target.value)}
          />

          <button type="submit">Delete Employee</button>
        </form>

        {message && <p>{message}</p>}
      </div>
    </>
  );
};

export default DeleteEmp;