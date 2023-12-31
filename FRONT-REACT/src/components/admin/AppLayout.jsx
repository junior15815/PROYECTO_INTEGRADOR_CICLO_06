import React from 'react';
import SSidebar from "./SSidenav";
import NNavbar from "./NNavbar";
import Alumnocrud from "../../pages/Alumnocrud";
const AppLayout = ({ children }) => {
  
    return (
      <div class="m-0 font-sans text-base antialiased font-normal leading-default bg-gray-50 text-slate-500 ">
        {/* SSIDEBAR */}
     <div className=''>  <SSidebar/></div>
         {/* SSIDEBAR */}
  
      <div class="ease-soft-in-out xl:ml-60  h-full max-h-screen rounded-xl transition-all duration-200">
        {/* NAV */}
          <NNavbar/>
       
         {/* NAV */}
       
       {/* CAR */}
       <div class="w-full px-6 py-6 mx-auto  ">
          {children}
        {/* <Alumnocrud/> */}
        </div>
         {/* CAR */}

      </div>
    </div>

    );
}

export default AppLayout;