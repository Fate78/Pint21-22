<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Reuniao
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Me.DataGridView1 = New System.Windows.Forms.DataGridView()
        Me.NREUNIAODataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.NSALADataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.NGESTORDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.NREQDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.OBSDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.REUNIAOBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.BDbEdi2021DataSet6 = New AnalisedeSistemas_Basic.BDbEdi2021DataSet6()
        Me.ButtonAnterior = New System.Windows.Forms.Button()
        Me.ButtonFechar = New System.Windows.Forms.Button()
        Me.ButtonProximo = New System.Windows.Forms.Button()
        Me.ButtonApagar = New System.Windows.Forms.Button()
        Me.ButtonGuardar = New System.Windows.Forms.Button()
        Me.ButtonNovo = New System.Windows.Forms.Button()
        Me.TextBox4 = New System.Windows.Forms.TextBox()
        Me.TextBox3 = New System.Windows.Forms.TextBox()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.Label4 = New System.Windows.Forms.Label()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.REUNIAOTableAdapter = New AnalisedeSistemas_Basic.BDbEdi2021DataSet6TableAdapters.REUNIAOTableAdapter()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.REUNIAOBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSet6, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'DataGridView1
        '
        Me.DataGridView1.AllowUserToOrderColumns = True
        Me.DataGridView1.AutoGenerateColumns = False
        Me.DataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DataGridView1.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.NREUNIAODataGridViewTextBoxColumn, Me.NSALADataGridViewTextBoxColumn, Me.NGESTORDataGridViewTextBoxColumn, Me.NREQDataGridViewTextBoxColumn, Me.OBSDataGridViewTextBoxColumn})
        Me.DataGridView1.DataSource = Me.REUNIAOBindingSource
        Me.DataGridView1.Location = New System.Drawing.Point(12, 13)
        Me.DataGridView1.Name = "DataGridView1"
        Me.DataGridView1.Size = New System.Drawing.Size(564, 258)
        Me.DataGridView1.TabIndex = 86
        '
        'NREUNIAODataGridViewTextBoxColumn
        '
        Me.NREUNIAODataGridViewTextBoxColumn.DataPropertyName = "NREUNIAO"
        Me.NREUNIAODataGridViewTextBoxColumn.HeaderText = "NREUNIAO"
        Me.NREUNIAODataGridViewTextBoxColumn.Name = "NREUNIAODataGridViewTextBoxColumn"
        Me.NREUNIAODataGridViewTextBoxColumn.ReadOnly = True
        '
        'NSALADataGridViewTextBoxColumn
        '
        Me.NSALADataGridViewTextBoxColumn.DataPropertyName = "NSALA"
        Me.NSALADataGridViewTextBoxColumn.HeaderText = "NSALA"
        Me.NSALADataGridViewTextBoxColumn.Name = "NSALADataGridViewTextBoxColumn"
        '
        'NGESTORDataGridViewTextBoxColumn
        '
        Me.NGESTORDataGridViewTextBoxColumn.DataPropertyName = "NGESTOR"
        Me.NGESTORDataGridViewTextBoxColumn.HeaderText = "NGESTOR"
        Me.NGESTORDataGridViewTextBoxColumn.Name = "NGESTORDataGridViewTextBoxColumn"
        '
        'NREQDataGridViewTextBoxColumn
        '
        Me.NREQDataGridViewTextBoxColumn.DataPropertyName = "NREQ"
        Me.NREQDataGridViewTextBoxColumn.HeaderText = "NREQ"
        Me.NREQDataGridViewTextBoxColumn.Name = "NREQDataGridViewTextBoxColumn"
        '
        'OBSDataGridViewTextBoxColumn
        '
        Me.OBSDataGridViewTextBoxColumn.DataPropertyName = "OBS"
        Me.OBSDataGridViewTextBoxColumn.HeaderText = "OBS"
        Me.OBSDataGridViewTextBoxColumn.Name = "OBSDataGridViewTextBoxColumn"
        '
        'REUNIAOBindingSource
        '
        Me.REUNIAOBindingSource.DataMember = "REUNIAO"
        Me.REUNIAOBindingSource.DataSource = Me.BDbEdi2021DataSet6
        '
        'BDbEdi2021DataSet6
        '
        Me.BDbEdi2021DataSet6.DataSetName = "BDbEdi2021DataSet6"
        Me.BDbEdi2021DataSet6.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'ButtonAnterior
        '
        Me.ButtonAnterior.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonAnterior.Location = New System.Drawing.Point(420, 493)
        Me.ButtonAnterior.Name = "ButtonAnterior"
        Me.ButtonAnterior.Size = New System.Drawing.Size(105, 24)
        Me.ButtonAnterior.TabIndex = 85
        Me.ButtonAnterior.Text = "<--------------"
        Me.ButtonAnterior.UseVisualStyleBackColor = True
        '
        'ButtonFechar
        '
        Me.ButtonFechar.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonFechar.Location = New System.Drawing.Point(772, 16)
        Me.ButtonFechar.Name = "ButtonFechar"
        Me.ButtonFechar.Size = New System.Drawing.Size(30, 30)
        Me.ButtonFechar.TabIndex = 84
        Me.ButtonFechar.Text = "X"
        Me.ButtonFechar.UseVisualStyleBackColor = True
        '
        'ButtonProximo
        '
        Me.ButtonProximo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonProximo.Location = New System.Drawing.Point(569, 493)
        Me.ButtonProximo.Name = "ButtonProximo"
        Me.ButtonProximo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonProximo.TabIndex = 83
        Me.ButtonProximo.Text = "-------------->"
        Me.ButtonProximo.UseVisualStyleBackColor = True
        '
        'ButtonApagar
        '
        Me.ButtonApagar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonApagar.Location = New System.Drawing.Point(420, 334)
        Me.ButtonApagar.Name = "ButtonApagar"
        Me.ButtonApagar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonApagar.TabIndex = 82
        Me.ButtonApagar.Text = "Apagar"
        Me.ButtonApagar.UseVisualStyleBackColor = True
        '
        'ButtonGuardar
        '
        Me.ButtonGuardar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonGuardar.Location = New System.Drawing.Point(569, 294)
        Me.ButtonGuardar.Name = "ButtonGuardar"
        Me.ButtonGuardar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonGuardar.TabIndex = 81
        Me.ButtonGuardar.Text = "Guardar"
        Me.ButtonGuardar.UseVisualStyleBackColor = True
        '
        'ButtonNovo
        '
        Me.ButtonNovo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonNovo.Location = New System.Drawing.Point(420, 294)
        Me.ButtonNovo.Name = "ButtonNovo"
        Me.ButtonNovo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonNovo.TabIndex = 80
        Me.ButtonNovo.Text = "Novo"
        Me.ButtonNovo.UseVisualStyleBackColor = True
        '
        'TextBox4
        '
        Me.TextBox4.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.REUNIAOBindingSource, "OBS", True))
        Me.TextBox4.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox4.Location = New System.Drawing.Point(145, 414)
        Me.TextBox4.Name = "TextBox4"
        Me.TextBox4.Size = New System.Drawing.Size(214, 23)
        Me.TextBox4.TabIndex = 79
        '
        'TextBox3
        '
        Me.TextBox3.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.REUNIAOBindingSource, "NREQ", True))
        Me.TextBox3.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox3.Location = New System.Drawing.Point(145, 374)
        Me.TextBox3.Name = "TextBox3"
        Me.TextBox3.Size = New System.Drawing.Size(214, 23)
        Me.TextBox3.TabIndex = 78
        '
        'TextBox2
        '
        Me.TextBox2.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.REUNIAOBindingSource, "NGESTOR", True))
        Me.TextBox2.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox2.Location = New System.Drawing.Point(145, 334)
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.Size = New System.Drawing.Size(214, 23)
        Me.TextBox2.TabIndex = 77
        '
        'TextBox1
        '
        Me.TextBox1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.REUNIAOBindingSource, "NSALA", True))
        Me.TextBox1.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox1.Location = New System.Drawing.Point(145, 294)
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.Size = New System.Drawing.Size(214, 23)
        Me.TextBox1.TabIndex = 76
        '
        'Label4
        '
        Me.Label4.AutoSize = True
        Me.Label4.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label4.Location = New System.Drawing.Point(12, 414)
        Me.Label4.Name = "Label4"
        Me.Label4.Size = New System.Drawing.Size(122, 24)
        Me.Label4.TabIndex = 75
        Me.Label4.Text = "Observações"
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label3.Location = New System.Drawing.Point(12, 374)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(127, 24)
        Me.Label3.TabIndex = 74
        Me.Label3.Text = "NRequisitante"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label2.Location = New System.Drawing.Point(12, 334)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(79, 24)
        Me.Label2.TabIndex = 73
        Me.Label2.Text = "NGestor"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label1.Location = New System.Drawing.Point(12, 294)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(60, 24)
        Me.Label1.TabIndex = 72
        Me.Label1.Text = "NSala"
        '
        'REUNIAOTableAdapter
        '
        Me.REUNIAOTableAdapter.ClearBeforeFill = True
        '
        'Reuniao
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(814, 531)
        Me.Controls.Add(Me.DataGridView1)
        Me.Controls.Add(Me.ButtonAnterior)
        Me.Controls.Add(Me.ButtonFechar)
        Me.Controls.Add(Me.ButtonProximo)
        Me.Controls.Add(Me.ButtonApagar)
        Me.Controls.Add(Me.ButtonGuardar)
        Me.Controls.Add(Me.ButtonNovo)
        Me.Controls.Add(Me.TextBox4)
        Me.Controls.Add(Me.TextBox3)
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.Label4)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Name = "Reuniao"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Reuniao"
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.REUNIAOBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSet6, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub

    Friend WithEvents DataGridView1 As DataGridView
    Friend WithEvents ButtonAnterior As Button
    Friend WithEvents ButtonFechar As Button
    Friend WithEvents ButtonProximo As Button
    Friend WithEvents ButtonApagar As Button
    Friend WithEvents ButtonGuardar As Button
    Friend WithEvents ButtonNovo As Button
    Friend WithEvents TextBox4 As TextBox
    Friend WithEvents TextBox3 As TextBox
    Friend WithEvents TextBox2 As TextBox
    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Label4 As Label
    Friend WithEvents Label3 As Label
    Friend WithEvents Label2 As Label
    Friend WithEvents Label1 As Label
    Friend WithEvents BDbEdi2021DataSet6 As BDbEdi2021DataSet6
    Friend WithEvents REUNIAOBindingSource As BindingSource
    Friend WithEvents REUNIAOTableAdapter As BDbEdi2021DataSet6TableAdapters.REUNIAOTableAdapter
    Friend WithEvents NREUNIAODataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents NSALADataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents NGESTORDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents NREQDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents OBSDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
End Class
