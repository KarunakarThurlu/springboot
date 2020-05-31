import React from "react";
import Sidebar from "./Sidebar";
import "../App.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faHome} from "@fortawesome/free-solid-svg-icons"
import { faAlignJustify } from "@fortawesome/free-solid-svg-icons";
import { Navbar, Nav } from "react-bootstrap";

function Home() {
  return (
    <div className="app">
      <Navbar className="rnavbar navbar-expand-sm bg-dark navbar-dark">
        <Nav.Item className="col-5">
          <Nav.Link href="/home" className="navfont">
            <FontAwesomeIcon
              icon={faAlignJustify}
              onClick={() => <Sidebar />}
              style={{ fontSize: "30px" }}
            />
          </Nav.Link>
          <Sidebar />
        </Nav.Item>
        <Nav.Item className="col-6">
          <Nav.Link href="/home" className="navfont">
            <i
              className="fa fa-cubes"
              aria-hidden="true"
              style={{ fontSize: "30px" }}
            ></i>
            <FontAwesomeIcon icon={faHome}/>
            DataAnalyticsApplication
          </Nav.Link>
        </Nav.Item>
        <Nav.Item className="col">
          <Nav.Link href="/logout" className="navfont">
            Logout
          </Nav.Link>
        </Nav.Item>
      </Navbar>
    </div>
  );
}

export default Home;
