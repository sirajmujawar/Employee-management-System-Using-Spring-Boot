import { useEffect, useState } from "react";
import "../css/App.css";

const RecordEmp = () => {
  const [selectedEmp, setSelectedEmp] = useState(null);
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/employees")
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setEmployees(data.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const handleDelete = (id) => {
    fetch(`http://localhost:8080/employees/${id}`, {
      method: "DELETE",
    })
      .then(() => {
        setEmployees((prev) => prev.filter((emp) => emp.empid !== id));
      })
      .catch((err) => console.error(err));
  };

  {selectedEmp && (
  <div className="edit-form">
    <h2>Edit Employee</h2>

    <input
      type="text"
      value={selectedEmp.name}
      onChange={(e) =>
        setSelectedEmp({ ...selectedEmp, name: e.target.value })
      }
    />

    <input
      type="text"
      value={selectedEmp.email}
      onChange={(e) =>
        setSelectedEmp({ ...selectedEmp, email: e.target.value })
      }
    />

    <input
      type="number"
      value={selectedEmp.salary}
      onChange={(e) =>
        setSelectedEmp({ ...selectedEmp, salary: e.target.value })
      }
    />

    <button onClick={handleUpdate}>Update</button>
  </div>
)}
  return (
    <>
      <h1>Employee Records</h1>

      <div className="table-container">
        <table>
          <thead>
            <tr>
              <th>EmpId</th>
              <th>Name</th>
              <th>Email</th>
              <th>Salary</th>
              <th>Department</th>
              <th>Location</th>
              <th>Actions</th>
            </tr>
          </thead>

          <tbody>
            {employees.map((emp) => (
              <tr key={emp.empid}>
                <td>{emp.empid}</td>
                <td>{emp.name}</td>
                <td>{emp.email}</td>
                <td>{emp.salary}</td>
                <td>{emp.department}</td>
                <td>{emp.location}</td>
                <td>
                <button
  className="edit-btn"
  onClick={() => setSelectedEmp(emp)}
>
  Edit
</button>
                  <button
                    className="delete-btn"
                    onClick={() => handleDelete(emp.empid)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
};

export default RecordEmp;