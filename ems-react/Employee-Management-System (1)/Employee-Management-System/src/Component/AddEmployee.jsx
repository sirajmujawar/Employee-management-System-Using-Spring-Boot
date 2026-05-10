import { useState } from "react";

const AddEmployee = () => {
  const[error,setErrors]=useState({});
const saveEmployee = async () => {
  try {
    await axios.post("http://localhost:8080/employees", employee);
    alert("Employee added successfully");
    setErrors({}); // clear errors
  } catch (error) {
    if (error.response && error.response.data) {
      setErrors(error.response.data); // store backend errors
    }
  }
};

  const [employee, setEmployee] = useState({
    name: "",
    email: "",
    salary: "",
    department: "",
    location: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setEmployee((prev) => ({
      ...prev,...employee,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // ✅ Validation
    if (!employee.name || !employee.email || !employee.salary || !employee.department || !employee.location) {
      setMessage("All fields are required!");
      return;
    }

    console.log(employee);

    // 🔥 API Call
    fetch("http://localhost:8080/employees", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        ...employee,
        salary: Number(employee.salary), // convert to number
      }),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        setMessage("Employee added successfully!");
      })
      .catch((err) => console.error(err));

    // reset form
    setEmployee({
      name: "",
      email: "",
      salary: "",
      department: "",
      location: "",
    });
  };

  return (
    <>
      <h1>Add Employee</h1>

      <div className="emp">
        <form onSubmit={handleSubmit}>
          
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
            placeholder="Enter Email"
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
            placeholder="Enter Location"
            value={employee.location}
            onChange={handleChange}
          />

          <button type="submit">Add Employee</button>
        </form>

        {message && <p>{message}</p>}
      </div>
    </>
  );
};

export default AddEmployee;