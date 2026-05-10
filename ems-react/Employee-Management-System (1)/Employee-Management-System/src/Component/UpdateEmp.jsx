import { useState } from "react";
import "../css/App.css";

const UpdateEmp = () => {
  const [employee, setEmployee] = useState({
    empid: "",          // ✅ fixed
    name: "",           // ✅ fixed
    email: "",          // ✅ add this
    salary: "",
    department: "",     // ✅ fixed
    location: "",       // ✅ add this
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!employee.empid) {
      setMessage("Employee ID is required to update");
      return;
    }

    console.log("Updated Employee:", employee);

   const { empid, ...data } = employee;

fetch(`http://localhost:8080/employees/${empid}`, {
  method: "PUT",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify({
    ...data,
    salary: Number(data.salary),   // ✅ convert to number
  }),
})
      .then((res) => res.json())
      .then(() => setMessage("Employee updated successfully"))
      .catch(() => setMessage("Error updating employee"));
  };

  return (
    <>
      <h1>Update Employee</h1>

      <div className="emp">
        <form onSubmit={handleSubmit}>
          
          <label>EmpId</label>
          <input
            type="number"
            name="empid"
            value={employee.empid}
            onChange={handleChange}
          />

          <label>Name</label>
          <input
            type="text"
            name="name"
            value={employee.name}
            onChange={handleChange}
          />

          <label>Email</label>
          <input
            type="email"
            name="email"
            value={employee.email}
            onChange={handleChange}
          />

          <label>Salary</label>
          <input
            type="number"
            name="salary"
            value={employee.salary}
            onChange={handleChange}
          />

          <label>Department</label>
          <select
            name="department"
            value={employee.department}
            onChange={handleChange}
          >
            <option value="">Select Department</option>
            <option value="HR">HR</option>
            <option value="IT">IT</option>
            <option value="Finance">Finance</option>
          </select>

          <label>Location</label>
          <input
            type="text"
            name="location"
            value={employee.location}
            onChange={handleChange}
          />

          <button type="submit">Update Employee</button>
        </form>

        {message && <p>{message}</p>}
      </div>
    </>
  );
};

export default UpdateEmp;