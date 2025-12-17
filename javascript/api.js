 async function protectedData() {
        const token = localStorage.getItem("jwtToken");
        try {
            const response = await fetch("http://localhost:8080/users/login",{
                headers:{
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.status === 403 || response.status === 401) {
                console.error("Authorization failed. Redirecting to login.");
                localStorage.removeItem("jwtToken"); 
                window.location.href = "login.html";
                return; // Stop processing
            }

            if (!response.ok) {
                // Handle other HTTP errors (e.g., 500, 404)
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            return data;
        } catch (error) {
            console.error("API call fail",error)
        }
    }
    