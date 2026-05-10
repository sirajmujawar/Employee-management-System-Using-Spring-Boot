import "../css/App.css"

const Navbar = () => {
  return (
    <>
      <div className="nav">
        <div className="logo">Employee-Management-System</div>
        <div className="nav-items">        
            <ul>
          <li><a href="/">Add Employee</a></li>
          <li><a href="/update">update Employee</a></li>
          <li><a href="/delete">Delete Employee</a></li>
            <li><a href="/records">Records Employee</a></li>
        </ul>
        </div>

      </div>
    </>
  );
};

export default Navbar;