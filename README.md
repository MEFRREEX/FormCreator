# FormCreator
Tool for easy creation of forms in Json file for Nukkit

## Dependencies
> [!note]
> This plugin is dependent on the FormConstructor plugin     
> GitHub: https://github.com/MEFRREEX/FormConstructor/releases

## How to use
Example form:

`example.json`
```json
{
    "command": {
        "enable": true,
        "name": "example",
        "description": "Example command",
        "aliases": [
            "example1"
        ],
        "permission": "formcreator.example"
    },
    "title": "Example Form",
    "content": [
        "Content line"
    ],
    "buttons": [
        {
            "name": "Button",
            "image": "textures/blocks/diamond",
            "imageType": "path",
            "actions": [
                {
                    "type": "OPEN_FORM",
                    "value": "example"
                }
            ]
        },
        {
            "name": "Button 2",
            "actions": [
                {
                    "type": "PLAYER_COMMAND",
                    "value": "say Test"
                }
            ]
        }
    ],
    "openActions": [
        {
            "type": "CONSOLE_COMMAND",
            "value": "say %player% Form opened!"
        }
    ],
    "closeActions": [
        {
            "type": "CONSOLE_COMMAND",
            "value": "say %player% Form closed!"
        }
    ]
}
```
Registration of the form:

`forms.yml`
```yml
# Registration of the form
forms:
  # form name: "form path in the 'forms' folder"
  example: "example.json"
```

## Commands
| Name         | Sub Command | Usage                             | Description      | Permission       |
|--------------|-------------|-----------------------------------|------------------|------------------|
| /formcreator | open        | /formcreator open <form> <player> | Open player form | formcreator.open |
| /formcreator | info        | /formcreator info                 | Plugin info      | formcreator.info |
| /formcreator | help        | /formcreator help                 | Help             |                  |

## API
### FormManager
Get a folder with forms:
```java
File folder = FormManager.getFormsFolder();
```
Getting a form by name:
```java
Form form = FormManager.get("form_name");
```
Check the existence of the form:
```java
FormManager.exists("form_name");
```
Load form from file:
```java
FormManager.load("form_name", file);
```

### Creating a custom action executor

Your executor:
```java
public class YourExecutor implements Executor {

    /**
     * @param player Player
     * @param value  Value of action
     */
    @Override
    public void execute(Player player, String value) {
        // Handling action
    }
}
```

Executor registration:
```java
// name - Executor name. For example: YOUR_EXECUTOR 
ExecutorManager.register("YOUR_EXECUTOR", new YourExecutor());
```
Getting a executor:
```java
ExecutorManager.getExecutor("YOUR_EXECUTOR");
```

### Events
| Name          | Cancellable | Description                    |
|---------------|-------------|--------------------------------|
| FormLoadEvent | true        | Called when the form is loaded |
| FormSendEvent | true        | Called when a form is opened   |
