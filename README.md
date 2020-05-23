# IN4MATX 124

## Project 3

```
Members:
Joshua Kuan (52594796)
Garry Fanata (39583521)
```

We are group 13, this project is NOT hosted on circinus-13. A zoom meeting is scheduled to demo this application.

Github Repo: `https://github.com/cwjoshuak/inf124-ecommerce-p2`

Our site consists of 4 main pages:-

- the home page: index.html
- products list page: products.php
- individual product page: product.php
- order confirmation page: order_confirmation.php

### Requirement Satisfaction

1. PHP and MySQL is used to dynamically generate the products list page, individual product page and order confirmation page.
2. The form input is validated on the client-side as the user fills out the form. After the user fills in the form on the individual product page and clicks on the Purchase button, the form input is sent through a `POST` request to `api/order.php` which uses a prepared SQL statement through `mysqli` to insert it into the `transactions` table.
3. After the `POST` request is successful, the server responds with a `transaction_id` which is used to redirect the user to the order confirmation page. This page retrieves the transaction listing based on the id and displays the details to the user.
4. We have two ajax features:
   - Typing in ZIP code will autofill City and State in the form
   - Typing in ZIP code will automatically calculate the tax rate and display it for the user

### Screenshots

![](assets/home.png)
![](assets/product-list.png)
![](assets/single-product.png)
![](assets/order-confirmation.png)

#### Notes:

- Files from project 1 have been removed from the repo which were unnecessary:-

```
    - js/products.js
    - js/product.js
    - products.html
    - product.html
```

- Necessary sql files are in the `sql/` folder.
- API files are in the `api/` folder .
