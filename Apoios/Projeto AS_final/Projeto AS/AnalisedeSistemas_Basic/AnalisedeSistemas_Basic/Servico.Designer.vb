<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Servico
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
        Me.NSERVICODataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.TIPODataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.OBSDataGridViewTextBoxColumn = New System.Windows.Forms.DataGridViewTextBoxColumn()
        Me.SERVICOBindingSource = New System.Windows.Forms.BindingSource(Me.components)
        Me.BDbEdi2021DataSet10 = New AnalisedeSistemas_Basic.BDbEdi2021DataSet10()
        Me.ButtonAnterior = New System.Windows.Forms.Button()
        Me.ButtonFechar = New System.Windows.Forms.Button()
        Me.ButtonProximo = New System.Windows.Forms.Button()
        Me.ButtonApagar = New System.Windows.Forms.Button()
        Me.ButtonGuardar = New System.Windows.Forms.Button()
        Me.ButtonNovo = New System.Windows.Forms.Button()
        Me.TextBox2 = New System.Windows.Forms.TextBox()
        Me.TextBox1 = New System.Windows.Forms.TextBox()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.SERVICOTableAdapter = New AnalisedeSistemas_Basic.BDbEdi2021DataSet10TableAdapters.SERVICOTableAdapter()
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.SERVICOBindingSource, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.BDbEdi2021DataSet10, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.SuspendLayout()
        '
        'DataGridView1
        '
        Me.DataGridView1.AllowUserToOrderColumns = True
        Me.DataGridView1.AutoGenerateColumns = False
        Me.DataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize
        Me.DataGridView1.Columns.AddRange(New System.Windows.Forms.DataGridViewColumn() {Me.NSERVICODataGridViewTextBoxColumn, Me.TIPODataGridViewTextBoxColumn, Me.OBSDataGridViewTextBoxColumn})
        Me.DataGridView1.DataSource = Me.SERVICOBindingSource
        Me.DataGridView1.Location = New System.Drawing.Point(12, 12)
        Me.DataGridView1.Name = "DataGridView1"
        Me.DataGridView1.Size = New System.Drawing.Size(361, 258)
        Me.DataGridView1.TabIndex = 123
        '
        'NSERVICODataGridViewTextBoxColumn
        '
        Me.NSERVICODataGridViewTextBoxColumn.DataPropertyName = "NSERVICO"
        Me.NSERVICODataGridViewTextBoxColumn.HeaderText = "NSERVICO"
        Me.NSERVICODataGridViewTextBoxColumn.Name = "NSERVICODataGridViewTextBoxColumn"
        Me.NSERVICODataGridViewTextBoxColumn.ReadOnly = True
        '
        'TIPODataGridViewTextBoxColumn
        '
        Me.TIPODataGridViewTextBoxColumn.DataPropertyName = "TIPO"
        Me.TIPODataGridViewTextBoxColumn.HeaderText = "TIPO"
        Me.TIPODataGridViewTextBoxColumn.Name = "TIPODataGridViewTextBoxColumn"
        '
        'OBSDataGridViewTextBoxColumn
        '
        Me.OBSDataGridViewTextBoxColumn.DataPropertyName = "OBS"
        Me.OBSDataGridViewTextBoxColumn.HeaderText = "OBS"
        Me.OBSDataGridViewTextBoxColumn.Name = "OBSDataGridViewTextBoxColumn"
        '
        'SERVICOBindingSource
        '
        Me.SERVICOBindingSource.DataMember = "SERVICO"
        Me.SERVICOBindingSource.DataSource = Me.BDbEdi2021DataSet10
        '
        'BDbEdi2021DataSet10
        '
        Me.BDbEdi2021DataSet10.DataSetName = "BDbEdi2021DataSet10"
        Me.BDbEdi2021DataSet10.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema
        '
        'ButtonAnterior
        '
        Me.ButtonAnterior.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonAnterior.Location = New System.Drawing.Point(420, 492)
        Me.ButtonAnterior.Name = "ButtonAnterior"
        Me.ButtonAnterior.Size = New System.Drawing.Size(105, 24)
        Me.ButtonAnterior.TabIndex = 122
        Me.ButtonAnterior.Text = "<--------------"
        Me.ButtonAnterior.UseVisualStyleBackColor = True
        '
        'ButtonFechar
        '
        Me.ButtonFechar.Font = New System.Drawing.Font("Microsoft Sans Serif", 12.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonFechar.Location = New System.Drawing.Point(772, 12)
        Me.ButtonFechar.Name = "ButtonFechar"
        Me.ButtonFechar.Size = New System.Drawing.Size(30, 30)
        Me.ButtonFechar.TabIndex = 121
        Me.ButtonFechar.Text = "X"
        Me.ButtonFechar.UseVisualStyleBackColor = True
        '
        'ButtonProximo
        '
        Me.ButtonProximo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonProximo.Location = New System.Drawing.Point(569, 492)
        Me.ButtonProximo.Name = "ButtonProximo"
        Me.ButtonProximo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonProximo.TabIndex = 120
        Me.ButtonProximo.Text = "-------------->"
        Me.ButtonProximo.UseVisualStyleBackColor = True
        '
        'ButtonApagar
        '
        Me.ButtonApagar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonApagar.Location = New System.Drawing.Point(420, 333)
        Me.ButtonApagar.Name = "ButtonApagar"
        Me.ButtonApagar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonApagar.TabIndex = 119
        Me.ButtonApagar.Text = "Apagar"
        Me.ButtonApagar.UseVisualStyleBackColor = True
        '
        'ButtonGuardar
        '
        Me.ButtonGuardar.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonGuardar.Location = New System.Drawing.Point(569, 293)
        Me.ButtonGuardar.Name = "ButtonGuardar"
        Me.ButtonGuardar.Size = New System.Drawing.Size(105, 24)
        Me.ButtonGuardar.TabIndex = 118
        Me.ButtonGuardar.Text = "Guardar"
        Me.ButtonGuardar.UseVisualStyleBackColor = True
        '
        'ButtonNovo
        '
        Me.ButtonNovo.Font = New System.Drawing.Font("Microsoft Sans Serif", 8.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.ButtonNovo.Location = New System.Drawing.Point(420, 293)
        Me.ButtonNovo.Name = "ButtonNovo"
        Me.ButtonNovo.Size = New System.Drawing.Size(105, 24)
        Me.ButtonNovo.TabIndex = 117
        Me.ButtonNovo.Text = "Novo"
        Me.ButtonNovo.UseVisualStyleBackColor = True
        '
        'TextBox2
        '
        Me.TextBox2.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.SERVICOBindingSource, "OBS", True))
        Me.TextBox2.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox2.Location = New System.Drawing.Point(145, 333)
        Me.TextBox2.Name = "TextBox2"
        Me.TextBox2.Size = New System.Drawing.Size(214, 23)
        Me.TextBox2.TabIndex = 116
        '
        'TextBox1
        '
        Me.TextBox1.DataBindings.Add(New System.Windows.Forms.Binding("Text", Me.SERVICOBindingSource, "TIPO", True))
        Me.TextBox1.Font = New System.Drawing.Font("Microsoft Sans Serif", 10.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.TextBox1.Location = New System.Drawing.Point(145, 293)
        Me.TextBox1.Name = "TextBox1"
        Me.TextBox1.Size = New System.Drawing.Size(214, 23)
        Me.TextBox1.TabIndex = 115
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label2.Location = New System.Drawing.Point(12, 333)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(112, 24)
        Me.Label2.TabIndex = 114
        Me.Label2.Text = "Observação"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Font = New System.Drawing.Font("Microsoft Sans Serif", 14.0!, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.Label1.Location = New System.Drawing.Point(12, 293)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(48, 24)
        Me.Label1.TabIndex = 113
        Me.Label1.Text = "Tipo"
        '
        'SERVICOTableAdapter
        '
        Me.SERVICOTableAdapter.ClearBeforeFill = True
        '
        'Servico
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
        Me.Controls.Add(Me.TextBox2)
        Me.Controls.Add(Me.TextBox1)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Name = "Servico"
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen
        Me.Text = "Servico"
        CType(Me.DataGridView1, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.SERVICOBindingSource, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.BDbEdi2021DataSet10, System.ComponentModel.ISupportInitialize).EndInit()
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
    Friend WithEvents TextBox2 As TextBox
    Friend WithEvents TextBox1 As TextBox
    Friend WithEvents Label2 As Label
    Friend WithEvents Label1 As Label
    Friend WithEvents BDbEdi2021DataSet10 As BDbEdi2021DataSet10
    Friend WithEvents SERVICOBindingSource As BindingSource
    Friend WithEvents SERVICOTableAdapter As BDbEdi2021DataSet10TableAdapters.SERVICOTableAdapter
    Friend WithEvents NSERVICODataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents TIPODataGridViewTextBoxColumn As DataGridViewTextBoxColumn
    Friend WithEvents OBSDataGridViewTextBoxColumn As DataGridViewTextBoxColumn
End Class
